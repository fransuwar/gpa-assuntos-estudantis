Narrativa: 
Como servidor gostaria de visualizar a tabela de inscrições da seleção desejada.
Na página Inscrições, deverá ter uma tabela com as inscrições, contendo as seguintes colunas: Aluno, Análise dos Documentos, Entrevista, Visita Domiciliar, Parecer Final e Ranking.
Nas colunas Análise dos Documentos, Entrevista, Visita Domiciliar e Parecer Final deverão ter um ícone-botão de cor cinza sinalizEntãoo que, na etapa correspondente, ainda não foi emitido nenhum parecer.
O ícone-botão deverá mudar para cor verde, caso o parecer seja Deferido e vermelho para Indeferido.

@Meta
@Author: Allan Vidal
@Data: 19/10/2017

Cenário: 
01 Acessar o sistema e visualizar a tabela de inscrições
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 7
Então a página Visualizar Inscrições é exibida

Cenário:
02 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno deferido
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 7
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) Mariana Lima
Então Análise de Documentos Deferido cor verde
Então Entrevista Deferido cor verde
Então Visita Domiciliar Deferido cor verde
Então Parece Final Deferido cor verde

Cenário:
03 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em documentação
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 165
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) João Victor Gonçalves
Então Análise de Documentos Indeferido cor vermelho
Então Entrevista Indeferido cor vermelho
Então Visita Domiciliar Indeferido cor vermelho
Então Parece Final Indeferido cor vermelho

Cenário:
04 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em entrevista
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 135
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) Débora Lima Alves
Então Análise de Documentos Deferido cor verde
Então Entrevista Indeferido cor vermelho
Então Visita Domiciliar Indeferido cor vermelho
Então Parece Final Indeferido cor vermelho

Cenário:
05 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em visita domiciliar
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 136
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) Mariana Lima
Então Análise de Documentos Deferido cor verde
Então Entrevista Deferido cor verde
Então Visita Domiciliar Indeferido cor vermelho
Então Parece Final Indeferido cor vermelho

Cenário:
06 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno indeferido em parecer final
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 136
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) Débora Lima Alves
Então Análise de Documentos Deferido cor verde
Então Entrevista Deferido cor verde
Então Visita Domiciliar Deferido cor verde
Então Parece Final Indeferido cor vermelho

Cenário:
07 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno não avaliado
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 136
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) Guilherme Tavares
Então Análise de Documentos Não Avaliado cor cinza
Então Entrevista Não Avaliado cor cinza
Então Visita Domiciliar Não Avaliado cor cinza
Então Parece Final Não Avaliado cor cinza

Cenário:
08 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno apenas documentação deferida
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 52
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) João Pedro Almeida
Então Análise de Documentos Deferido cor verde
Então Entrevista Não Avaliado cor cinza
Então Visita Domiciliar Não Avaliado cor cinza
Então Parece Final Não Avaliado cor cinza

Cenário:
09 Acessar o sistema e visualizar a tabela de inscrições e verificar Aluno documentação e entrevista deferida
Dado que estou no site http://localhost:8080/auxilio/selecao
QuEntãoo clico em visualizar_inscricoes 52
Então a página Visualizar Inscrições é exibida
Então possui aluno(a) Mariana Lima
Então Análise de Documentos Deferido cor verde
Então Entrevista Deferido cor verde
Então Visita Domiciliar Não Avaliado cor cinza
Então Parece Final Não Avaliado cor cinza
