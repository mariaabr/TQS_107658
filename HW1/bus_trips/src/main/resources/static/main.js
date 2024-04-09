 // Dados de exemplo para as viagens (pode ser substituído pelos dados retornados pela API)
 const viagens = [
    { id: 1, from: 'Cidade A', to: 'Cidade B', horaPartida: '08:00', horaChegada: '12:00', preco: '50', tempoViagem: '4 horas' },
    { id: 2, from: 'Cidade B', to: 'Cidade C', horaPartida: '10:00', horaChegada: '14:00', preco: '60', tempoViagem: '4 horas' },
    { id: 3, from: 'Cidade C', to: 'Cidade D', horaPartida: '12:00', horaChegada: '16:00', preco: '70', tempoViagem: '4 horas' }
];

// Função para criar e adicionar os elementos da lista de viagens
function criarListaViagens() {
    const listaViagens = document.getElementById('lista-viagens');

    // Limpa qualquer conteúdo pré-existente na lista
    listaViagens.innerHTML = '';

    // Itera sobre os dados de viagem e cria um elemento de lista para cada viagem
    viagens.forEach(viagem => {
        const listItem = document.createElement('li');
        listItem.classList.add('list-group-item');

        // Constrói o conteúdo do item da lista com os dados da viagem
        listItem.innerHTML = `
            <div class="d-flex justify-content-between align-items-center">
                <div>
                    <span>ID: ${viagem.id}</span><br>
                    <span>Partida: ${viagem.horaPartida} - ${viagem.from}</span><br>
                    <span>Chegada: ${viagem.horaChegada} - ${viagem.to}</span><br>
                    <span>Preço: $${viagem.preco}</span><br>
                    <span>Tempo de Viagem: ${viagem.tempoViagem}</span>
                </div>
                <button class="btn btn-primary" onclick="selecionarViagem(${viagem.id})">Selecionar</button>
            </div>
        `;

        // Adiciona o item da lista à lista de viagens
        listaViagens.appendChild(listItem);
    });
}

// Função para simular a seleção de uma viagem (pode ser substituída pela lógica real de seleção)
function selecionarViagem(id) {
    alert('Viagem selecionada: ' + id);
}

// Chama a função para criar a lista de viagens quando a página carrega
window.onload = criarListaViagens;