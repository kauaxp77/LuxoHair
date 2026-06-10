const API_URL = 'http://localhost:8081';

document.addEventListener('DOMContentLoaded', async () => {
    const caixaTotal = document.getElementById('caixa-total');
    const tabelaAtendente = document.getElementById('tabela-atendente');

    try {
        const resposta = await fetch(`${API_URL}/agendamentos`);
        if (!resposta.ok) throw new Error('Erro ao carregar dados de agendamentos.');

        const agendamentos = await resposta.json();

        if (agendamentos.length === 0) {
            tabelaAtendente.innerHTML = `<tr><td colspan="5" class="text-center py-5 text-gray-400">Nenhum atendimento registrado hoje.</td></tr>`;
            return;
        }

        let somaCaixa = 0;

        tabelaAtendente.innerHTML = agendamentos.map(agenda => {
            const clienteNome = agenda.cliente ? agenda.cliente.nome : 'Cliente Geral';
            const clienteTel = agenda.cliente ? agenda.cliente.telefone : '(61) ---------';
            const servicoNome = agenda.servico ? agenda.servico.nome_servico : 'Serviço Geral';
            const profNome = agenda.profissional ? agenda.profissional.nome : 'Não designado';
            const preco = agenda.servico ? agenda.servico.preco : 0.0;

            somaCaixa += preco;

            return `
              <tr class="border-b hover:bg-gray-50 transition">
                <td class="py-5 font-semibold">${clienteNome}</td>
                <td>${clienteTel}</td>
                <td>${servicoNome}</td>
                <td class="text-emerald-700 font-bold"><i class="fa-solid fa-user-tie mr-2"></i>${profNome}</td>
                <td class="font-bold text-gray-900">R$ ${preco.toFixed(2)}</td>
              </tr>
            `;
        }).join('');

        caixaTotal.innerText = `R$ ${somaCaixa.toFixed(2)}`;

    } catch (error) {
        console.error(error);
        tabelaAtendente.innerHTML = `<tr><td colspan="5" class="text-center py-5 text-red-500">Erro de conexão com a API de recepção.</td></tr>`;
    }
});