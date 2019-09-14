use empresa;

# Função de agrupamento: SUM(somatória)
select sum(salario),s.nome from funcionario as f join setor as s on s.id = f.setor_id group by setor_id;

# Faixa salarial por genero
select sum(salario) as folha_pagamento, genero from funcionario group by genero order by folha_pagamento desc;

# AVG: Avarage (Média)
select avg(salario),s.nome from funcionario as f join setor as s on s.id = f.setor_id group by setor_id;

# MAX: O maior resultado
select max(salario),s.nome from funcionario as f join setor as s on s.id = f.setor_id group by setor_id;

# MIN: O menor resultado
select min(salario),s.nome from funcionario as f join setor as s on s.id = f.setor_id group by setor_id;