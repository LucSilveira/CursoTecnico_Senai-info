use genetica;

#Retorna somente a quantidade de ocorrências
select count(id) from pessoa;

#Quantas pessoas agrupadas pelo genero
select count(*),genero from pessoa group by genero;

#Quantidade de pessoas do genero feminino
select count(*),'FEMININO' from pessoa where genero = 'FEMININO';

#Agrupar as pessoas pela cor dos olhos da maior quantidade para a menor
select count(*),cor_olhos from pessoa group by cor_olhos order by cor_olhos desc;

#Qual a cor dos olhos mais recorrente no banco de dados
select count(*),cor_olhos from pessoa group by cor_olhos order by cor_olhos desc limit 1;