const API_URL = 'http://localhost:8081';

// Armazena o ID do cliente logado de forma fixa até o JWT retornar
// (Pode usar o ID 1 que criamos no primeiro teste de cliente)
const CLIENTE_LOGADO_ID = 1; 

document.addEventListener('DOMContentLoaded', async () => {
    const tabelaCliente = document.getElementById('tabela-cliente');
    const selectServico = document.getElementById('selectServico');
    const mensagemErro = document.getElementById('mensagemErro');
    const mensagemSucesso = document.getElementById('mensagemSucesso');
    const formAgendamento = document.getElementById('formAgendamento');
    const containerWhats = document.getElementById('containerWhats');
    const btnWhatsApp = document.getElementById('btnWhatsApp');

    // ==========================================
    // 1. CARREGA OS DADOS INICIAIS DA TELA (GET)
    // ==========================================
    async function carregarTela() {
        try {
            // Busca Agendamentos
            const resAgendas = await fetch(`${API_URL}/agendamentos`);
            if (!resAgendas.ok) throw new Error();
            const agendamentos = await resAgendas.json();

            // Renderiza Tabela
            if (agendamentos.length === 0) {
                tabelaCliente.innerHTML = `<tr><td colspan="4" class="text-center py-8 text-gray-400">Nenhum agendamento marcado.</td></tr>`;
            } else {
                tabelaCliente.innerHTML = agendamentos.map(agenda => {
                    const nomeServico = agenda.servico ? agenda.servico.nome_servico : 'Procedimento Geral';
                    const nomeProfissional = agenda.profissional ? agenda.profissional.nome : 'Profissional Técnico';
                    let dataFormatada = agenda.dataHora || 'A definir';
                    if (agenda.dataHora && agenda.dataHora.includes('T')) {
                        const partes = agenda.dataHora.split('T');
                        const dataInvertida = partes[0].split('-').reverse().join('/');
                        dataFormatada = `${dataInvertida} às ${partes[1].substring(0, 5)}`;
                    }
                    return `
                      <tr class="border-b text-gray-600 hover:bg-gray-50 transition">
                        <td class="py-5 font-semibold text-gray-900">${nomeServico}</td>
                        <td>${dataFormatada}</td>
                        <td class="font-bold text-gray-800"><i class="fa-solid fa-scissors mr-2 text-[#8B6B4A]"></i>${nomeProfissional}</td>
                        <td class="text-right"><span class="bg-green-50 text-green-700 px-3 py-1 rounded-full text-xs font-bold">Confirmado</span></td>
                      </tr>`;
                }).join('');
            }

            // Busca os Serviços do banco para popular o Select dinamicamente
            const resServicos = await fetch(`${API_URL}/admin/servicos`);
            if (resServicos.ok) {
                const servicos = await resServicos.json();
                selectServico.innerHTML = '<option value="">-- Selecione o Procedimento --</option>' + 
                    servicos.map(s => `<option value="${s.id_servico}" data-preco="${s.preco}" data-nome="${s.nome_servico}">${s.nome_servico} - R$ ${s.preco.toFixed(2)}</option>`).join('');
            }

        } catch (error) {
            mensagemErro.innerText = '🚨 Erro ao carregar dados do banco PostgreSQL.';
            mensagemErro.classList.remove('hidden');
        }
    }

    // Inicializa a listagem
    carregarTela();

    // ==========================================
    // 2. SALVA O NOVO AGENDAMENTO NO BANCO (POST)
    // ==========================================
    formAgendamento.addEventListener('submit', async (e) => {
        e.preventDefault();
        mensagemErro.classList.add('hidden');
        mensagemSucesso.classList.add('hidden');
        containerWhats.classList.add('hidden');

        // Captura os elementos digitados/selecionados na tela
        const idServico = document.getElementById('selectServico').value;
        const idProfissional = document.getElementById('selectProfissional').value;
        const dataHoraValue = document.getElementById('inputDataHora').value;

        // 1. Garanta que a data e hora termine com os segundos ":00" para o LocalDateTime do Java aceitar
        const dataHoraFormatada = dataHoraValue.includes(':') && dataHoraValue.split(':').length === 2 
            ? `${dataHoraValue}:00` 
            : dataHoraValue;

        // 2. Monte o objeto com as chaves EXATAS que estão declaradas nos seus arquivos .java
        const novoAgendamento = {
            id_cliente: parseInt(CLIENTE_LOGADO_ID),
            id_profissional: parseInt(idProfissional),
            id_servico: parseInt(idServico),
            data_hora: dataHoraFormatada, 
            status: "CONFIRMADO"
        };

        try {
            const resposta = await fetch(`${API_URL}/agendamentos`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(novoAgendamento)
            });

            if (resposta.status === 201 || resposta.ok) {
                mensagemSucesso.innerText = '✅ Horário reservado com sucesso no PostgreSQL!';
                mensagemSucesso.classList.remove('hidden');
                
                // Pega os dados amigáveis para o WhatsApp antes de resetar o formulário
                const optionSelecionada = selectServico.options[selectServico.selectedIndex];
                const nomeServicoText = optionSelecionada.getAttribute('data-nome');
                const profesionalText = document.getElementById('selectProfissional').options[document.getElementById('selectProfissional').selectedIndex].text;
                
                // Formata a data para a mensagem de texto amigável
                const dataSplit = dataHoraValue.split('T');
                const dataBr = dataSplit[0].split('-').reverse().join('/');
                const horaBr = dataSplit[1];

                // ==========================================
                // 3. ENGENHARIA DO LINK DO WHATSAPP (API)
                // ==========================================
                const numeroSalao = "5561999117396"; // WhatsApp do salão
                const mensagemZap = `Olá! Acabei de realizar um agendamento pelo site no salão Liso Luxo Hair:\n\n✨ *Procedimento:* ${nomeServicoText}\n📅 *Data:* ${dataBr}\n⏰ *Horário:* ${horaBr}\n💇‍♀️ *Profissional:* ${profesionalText}\n\nPor favor, confirmem meu horário no sistema!`;
                
                const urlWhatsapp = `https://api.whatsapp.com/send?phone=${numeroSalao}&text=${encodeURIComponent(mensagemZap)}`;
                
                btnWhatsApp.href = urlWhatsapp;
                containerWhats.classList.remove('hidden');

                formAgendamento.reset();
                carregarTela(); // Atualiza a tabela com o novo registro inserido
            } else {
                throw new Error();
            }

        } catch (error) {
            mensagemErro.innerText = '❌ Falha ao processar agendamento. Verifique se os IDs existem no banco.';
            mensagemErro.classList.remove('hidden');
        }
    });
});