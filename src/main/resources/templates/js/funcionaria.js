const API_URL = 'http://localhost:8081';

document.addEventListener('DOMContentLoaded', async () => {
    const nomeProfissionalSpan = document.getElementById('nome-profissional');
    const comissaoVal = document.getElementById('comissao-val');
    const tabelaTecnica = document.getElementById('tabela-tecnica');

    // Simula a profissional técnica que fez o login no sistema
    const profissionalLogada = "Gabrielle Silva";
    nomeProfissionalSpan.innerText = profissionalLogada;

    try {
        const resposta = await fetch(`${API_URL}/agendamentos`);
        if (!resposta.ok) throw new Error('Erro ao ler agendamentos.');

        const agendamentos = await resposta.json();

        // Filtra a nível de governança: só exibe dados da cadeira dela
        const meusTrabalhos = agendamentos.filter(a => a.profissional && a.profissional.nome === profissionalLogada);

        if (meusTrabalhos.length === 0) {
            tabelaTecnica.innerHTML = `<tr><td colspan="5" class="text-center py-5 text-gray-400">Nenhum procedimento registrado na sua cadeira ainda.</td></tr>`;
            return;
        }

        let faturamentoCadeira = 0;

        tabelaTecnica.innerHTML = meusTrabalhos.map(b => {
            const clienteNome = b.cliente ? b.cliente.nome : 'Cliente Geral';
            const servicoNome = b.servico ? b.servico.nome_servico : 'Serviço Geral';
            const bruto = b.servico ? b.servico.preco : 0.0;

            faturamentoCadeira += bruto;

            let dataFormatada = b.dataHora || 'Hoje';
            if (b.dataHora && b.dataHora.includes('T')) {
                dataFormatada = b.dataHora.split('T')[0].split('-').reverse().join('/');
            }

            return `
                <tr class="border-b text-gray-600 hover:bg-gray-50 transition">
                  <td class="py-5 font-semibold text-gray-900">${clienteNome}</td>
                  <td>${servicoNome}</td>
                  <td>${dataFormatada}</td>
                  <td class="font-bold">R$ ${bruto.toFixed(2)}</td>
                  <td class="text-right text-pink-600 font-bold text-sm"><i class="fa-solid fa-circle-check mr-1"></i>Sua Cadeira</td>
                </tr>
            `;
        }).join('');

        // Aplica a regra de negócio: 40% de comissão
        const comissaoAcumulada = faturamentoCadeira * 0.40;
        comissaoVal.innerText = `R$ ${comissaoAcumulada.toFixed(2)}`;

    } catch (error) {
        console.error(error);
        tabelaTecnica.innerHTML = `<tr><td colspan="5" class="text-center py-5 text-red-500">Erro ao carregar agenda técnica.</td></tr>`;
    }
});