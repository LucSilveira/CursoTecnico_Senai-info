use ianes_mt3;

#1
select count(*) as itens,a.nome from item as i join ambiente as a on a.id = i.cod_ambiente where  a.nome = 'Cozinha';

#2
select count(*) as patrimônios,c.nome from patrimonio as p join categoria as c on c.id = p.cod_categoria group by cod_categoria;

#3
select count(*) as quantidade,a.nome from item as i join ambiente as a on a.id = i.cod_ambiente group by cod_ambiente;

#4
select count(*) as quantidade,ambiente.nome from item as i join ambiente on ambiente.id = i.cod_ambiente group by cod_ambiente order by quantidade desc limit 1;
