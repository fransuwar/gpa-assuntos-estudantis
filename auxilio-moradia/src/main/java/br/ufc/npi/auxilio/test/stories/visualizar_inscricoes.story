Narrativa: 
Como servidor gostaria de visualizar a tabela de inscrições da seleção desejada.
Na página Inscrições, deverá ter uma tabela com as inscrições, contendo as seguintes colunas: Aluno, Análise dos Documentos, Entrevista, Visita Domiciliar, Parecer Final e Ranking.
Nas colunas Análise dos Documentos, Entrevista, Visita Domiciliar e Parecer Final deverão ter um ícone-botão de cor cinza sinalizEntãoo que, na etapa correspondente, ainda não foi emitido nenhum parecer.
O ícone-botão deverá mudar para cor verde, caso o parecer seja Deferido e vermelho para Indeferido.

@Meta
@Author: Allan Vidal
@Data: 19/10/2017

Cenário: #01 Acessar o sistema e visualizar a tabela de inscrições
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida

Exemplos:
|selecao|
|7|

Cenário: #02 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno deferido
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valor> e da cor <cor>
Então Entrevista deve estar com o status <valor> e da cor <cor>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valor|cor|
|7|Mariana Lima|Deferido|Verde|

Cenário: #03 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em documentação
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valor> e da cor <cor>
Então Entrevista deve estar com o status <valor> e da cor <cor>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valor|cor|
|165|João Victor Gonçalves|Indeferido|Vermelho|

Cenário: #04 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em entrevista
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valorD> e da cor <corD>
Então Entrevista deve estar com o status <valor> e da cor <cor>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valorD|corD|valor|cor|
|135|Débora Lima Alves|Deferido|Verde|Indeferido|Vermelho|

Cenário: #05 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em visita domiciliar
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valorDE> e da cor <corDE>
Então Entrevista deve estar com o status <valorDE> e da cor <corDE>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valorDE|corDE|valor|cor|
|136|Mariana Lima|Deferido|Verde|Indeferido|Vermelho|

Cenário: #06 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em parecer final
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valorDEV> e da cor <corDEV>
Então Entrevista deve estar com o status <valorDEV> e da cor <corDEV>
Então Visita Domiciliar deve estar com o status <valorDEV> e da cor <corDEV>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valorDEV|corDEV|valor|cor|
|136|Débora Lima Alves|Deferido|Verde|Indeferido|Vermelho|

Cenário: #07 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno não avaliado
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valor> e da cor <cor>
Então Entrevista deve estar com o status <valor> e da cor <cor>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valor|cor|
|136|Guilherme Tavares|Não Avaliado|Cinza|

Cenário: #08 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno apenas documentação deferida
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valorD> e da cor <corD>
Então Entrevista deve estar com o status <valor> e da cor <cor>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valorD|corD|valor|cor|
|52|João Pedro Almeida|Deferido|Verde|Não Avaliado|Cinza|

Cenário: #09 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno documentação e entrevista deferida
Dado que estou na página principal
Quando clico em visualizar Inscricoes da selecão <selecao>
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) <aluno>
Então Análise de Documentos deve estar com o status <valorDE> e da cor <corDE>
Então Entrevista deve estar com o status <valorDE> e da cor <corDE>
Então Visita Domiciliar deve estar com o status <valor> e da cor <cor>
Então Parece Final deve estar com o status <valor> e da cor <cor>

Exemplos:
|selecao|aluno|valorDE|corDE|valor|cor|
|52|Mariana Lima|Deferido|Verde|Não Avaliado|Cinza|