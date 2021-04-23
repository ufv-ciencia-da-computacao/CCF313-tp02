- Entidades
	- Status
		- Aberto, Fechado
	- Carta
		- id
		- Foreground
		- Background
		- Status
		DAO
			- MudarStatus
	- Tabuleiro
		- Construir tabuleiro

	- Jogo da Memória implements Jogos de Tabuleiro
		- Matriz de Carta
		- Construir Tabuleiro

		Controller Jogo da Memória
			- Abrir uma Carta
			- Fechar uma Carta
			- Verificar se duas Cartas tem o mesmo id (Retorna Falso ou Verdadeiro)

	- Jogador (Enum?)
		- id
		- nome
		- pontuação

		Controller
			- Atualizar Pontuação

	- Jogo
		- Tabuleiro
		- Player 1
		- Player 2
		- Jogador da Vez
		- Cartas Abertas
		Controller Jogo
			- Mudar jogador da Vez
