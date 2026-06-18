// URL base da sua API Java
const API_URL = 'http://localhost:8081';

// Mapeamento dos elementos da tela
const btnTabLogin = document.getElementById('btnTabLogin');
const btnTabCadastro = document.getElementById('btnTabCadastro');
const loginForm = document.getElementById('loginForm');
const cadastroForm = document.getElementById('cadastroForm');
const mensagemErro = document.getElementById('mensagemErro');
const mensagemSucesso = document.getElementById('mensagemSucesso');

// Alternância entre as abas Entrar e Criar Conta
btnTabLogin.addEventListener('click', () => switchTab('login'));
btnTabCadastro.addEventListener('click', () => switchTab('cadastro'));

function switchTab(type) {
  mensagemErro.classList.add('hidden');
  mensagemSucesso.classList.add('hidden');
  
  if (type === 'login') {
    loginForm.classList.remove('hidden');
    cadastroForm.classList.add('hidden');
    btnTabLogin.classList.add('tab-active');
    btnTabCadastro.classList.remove('tab-active');
  } else {
    loginForm.classList.add('hidden');
    cadastroForm.classList.remove('hidden');
    btnTabLogin.classList.remove('tab-active');
    btnTabCadastro.classList.add('tab-active');
  }
}

// ==========================================
// INTEGRAÇÃO REAL: CADASTRO DE CLIENTE (POST)
// ==========================================
cadastroForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  mensagemErro.classList.add('hidden');
  mensagemSucesso.classList.add('hidden');

  const novoCliente = {
    nome: document.getElementById('cadNome').value,
    telefone: document.getElementById('cadTelefone').value,
    email: document.getElementById('cadEmail').value
    // Se o seu modelo de banco exigir senha ou CPF, adicione o campo correspondente aqui
  };

  try {
    const resposta = await fetch(`${API_URL}/admin/clientes`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(novoCliente)
    });

    if (resposta.ok || resposta.status === 201) {
      mensagemSucesso.innerText = '✅ Conta de cliente criada com sucesso no PostgreSQL!';
      mensagemSucesso.classList.remove('hidden');
      cadastroForm.reset();
      setTimeout(() => switchTab('login'), 2000); // Muda para o login após 2 segundos
    } else {
      throw new Error('Falha no cadastro do servidor');
    }
  } catch (error) {
    mensagemErro.innerText = '🚨 Erro ao se conectar com o servidor Java.';
    mensagemErro.classList.remove('hidden');
  }
});

// ==========================================
// LOGICA DE LOGIN (Temporária até o JWT voltar)
// ==========================================
loginForm.addEventListener('submit', function(e) {
  e.preventDefault();
  mensagemErro.classList.add('hidden');
  
  const email = document.getElementById('loginEmail').value;
  const senha = document.getElementById('loginSenha').value;
  
  // Mantendo o roteamento inteligente baseado nas credenciais que o grupo projetou
  if (senha === '123456') {
    if (email === 'gerente@lisoluxo.com') window.location.href = 'dashboard-gerente.html';
    else if (email === 'recepcao@lisoluxo.com') window.location.href = 'dashboard-atendente.html';
    else if (email === 'estetica@lisoluxo.com') window.location.href = 'dashboard-funcionaria.html';
    else if (email === 'cliente@gmail.com') window.location.href = 'cliente.html';
    else {
      mensagemErro.innerText = 'Usuário não cadastrado para os dashboards.';
      mensagemErro.classList.remove('hidden');
    }
  } else {
    mensagemErro.innerText = 'Senha ou e-mail incorretos.';
    mensagemErro.classList.remove('hidden');
  }
});