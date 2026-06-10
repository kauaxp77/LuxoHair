const API_URL = 'http://localhost:8081';

document.addEventListener('DOMContentLoaded', async () => {
    // Captura os elementos de exibição financeira dos cards
    const cardBruto = document.getElementById('faturamento-bruto');
    const cardComissoes = document.getElementById('comissoes-totais');
    const cardLiquido = document.getElementById('retencao-liquida');
    
    const tabelaHistorico = document.getElementById('tabela-historico-gerente');
    const tabelaColaboradores = document.getElementById('tabela-colaboradores');

    // 1. Simulação da listagem de colaboradores fixos para controle de governança
    const equipe = [
        { nome: 'Gabrielle Silva', cargo: 'funcionaria', descricao: 'Funcionária (Área Técnica / Agenda Isolada)' },
        { nome: 'Juliana Medeiros', cargo: 'funcionaria', descricao: 'Funcionária (Área Técnica / Agenda Isolada)' }
    ];

    tabelaColaboradores.innerHTML = equipe.map(colab => `
      <tr class="border-b text-gray-600">
        <td class="py-4 font-semibold">${colab.nome}</td>
        <td>
          <select class="bg-[#f6f6f6] p-2 rounded-xl text-gray-800 outline-none border-none font-medium">
            <option value="funcionaria" selected>${colab.descricao}</option>
            <option value="atendente">Recepcionista (Visão Global de Caixa)</option>
          </select>
        </td>
        <td class="text-right"><button onclick="alert('Nível de acesso de ${colab.nome} atualizado no sistema!')" class="text-[#8B6B4A] font-bold hover:underline">Atualizar</button></td>
      </tr>
    `).join('');

    try {
        // 2. Busca os agendamentos do banco para consolidar o financeiro
        const resposta = await fetch(`${API_URL}/agendamentos`);
        if (!resposta.ok) throw new Error('Falha ao conectar com o banco de dados.');
        
        const agendamentos = await resposta.json();

        if (agendamentos.length === 0) {
            tabelaHistorico.innerHTML = `<tr><td colspan="4" class="text-center py-5 text-gray-400">Nenhum ganho registrado no banco ainda.</td></tr>`;
            return;
        }

        let faturamentoBrutoTotal = 0;

        // 3. Monta as linhas da tabela financeira puxando os valores reais do banco
        tabelaHistorico.innerHTML = agendamentos.map(agenda => {
            const clienteNome = agenda.cliente ? agenda.cliente.nome : 'Cliente Geral';
            const servicoNome = agenda.servico ? agenda.servico.nome_servico : 'Serviço Geral';
            const precoBruto = agenda.servico ? agenda.servico.preco : 0.0;
            
            // Acumula o faturamento bruto do salão baseado no preço de cada serviço agendado
            faturamentoBrutoTotal += precoBruto;

            let dataFormatada = agenda.dataHora || 'A definir';
            if (agenda.dataHora && agenda.dataHora.includes('T')) {
                const partes = agenda.dataHora.split('T');
                const dataInvertida = partes[0].split('-');
                dataFormatada = `${dataInvertida[2]}/${dataInvertida[1]}/${dataInvertida[0]}`;
            }

            return `
              <tr class="border-b hover:bg-gray-50 transition">
                <td class="py-5 font-semibold">${clienteNome}</td>
                <td>${servicoNome}</td>
                <td>${dataFormatada}</td>
                <td class="text-right font-black text-green-600">R$ ${precoBruto.toFixed(2)}</td>
              </tr>
            `;
        }).join('');

        // 4. Executa a Regra de Negócio Financeira do Liso Luxo Hair
        const taxaComissao = 0.40; // 40% vai para as profissionais técnicos
        const totalComissoes = faturamentoBrutoTotal * taxaComissao;
        const totalRetencaoLiquida = faturamentoBrutoTotal - totalComissoes;

        // Injeta os valores calculados dinamicamente nos cards superiores da tela
        cardBruto.innerText = `R$ ${faturamentoBrutoTotal.toFixed(2)}`;
        cardComissoes.innerText = `R$ ${totalComissoes.toFixed(2)}`;
        cardLiquido.innerText = `R$ ${totalRetencaoLiquida.toFixed(2)}`;

    } catch (error) {
        console.error('Erro na dashboard do gerente:', error);
        tabelaHistorico.innerHTML = `<tr><td colspan="4" class="text-center py-5 text-red-500">Erro ao processar balanço financeiro da API.</td></tr>`;
    }
});