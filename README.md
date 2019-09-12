# amestarwars

Projeto REST criado para o desafio técnico de Back-End

# antes de começar

É necessário que o servidor do banco de dados MongoDB esteja instalado corretamente na máquina. A aplicação não utiliza nenhum usuário, e utiliza a porta padrão do servidor. 
Será criado um bd chamado **amestarwars**, e também uma coleção chamada de **planetas**, onde conterão os documentos utilizados pela aplicação

# endpoints
A parte imutável para os endpoints é: **127.0.0.1:8080/api/planeta**

#### Métodos GET

##### /carregaMassa
A fim de facilitar a utilização do projeto, esse método faz a carga para o banco de dados de 61 planetas, pegando tais informações do site https://swapi.co . Tal método é alguns segundos mais demorado que o restante, visto que são carregadas informações de uma origem externa.

##### /
Lista todos os planetas

##### /nome/{nome}
Faz uma busca pelo nome do planeta

##### /id/{id}
Faz uma busca pelo id do planeta

#### Método POST

##### /
Através de um JSON, se é adicionado um planeta ao banco de dados. Exemplo de JSON:

> {
>     "nome": "Planeta",
>     "clima": "Clima",
>     "terreno": "Planície",
>     "quantidadeAparicoes": 700
> }

Observação: nome, clima e terreno são requeridos, logo podem até ser vazios, porém não nulos. Além disso, não são permitidos nomes idênticos.

#### Método DELETE

##### /{id}
Utilizando o ID como referência, é possível remover planetas do banco de dados

# ambiente

Para a criação desse projeto, foram utilizadas as seguintes ferramentas:

OS: Linux Mint
IDE: Eclipse
Versão do Java: 1.8
Frameworks utilizados: Spring Boot
Banco de dados utilizado: MongoDB
