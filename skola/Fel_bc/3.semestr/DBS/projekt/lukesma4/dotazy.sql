insert into barva (id, nazev) values ( SELECT MAX(id)+1 
FROM barva, 'ruzova'
);

delete from barva where (id= (SELECT MAX(id) 
FROM barva));
select * from barva;

select barva_kart_id, count (barva_kart_id)
from mtg_karta
where id > 10
group by barva_kart_id
having count (barva_kart_id) > 20;

SELECT nazev, id, 'druh' FROM druh
 UNION
SELECT nazev, id, 'format' FROM format ;

select  'karta maindeck', jmeno, pocet from mtg_karta join md_obsahuje on ( karta_md_id = mtg_karta.id )  
union 
select  'karta sideboard', jmeno, pocet from mtg_karta join sb_obsahuje on ( karta_sb_id = mtg_karta.id );  


select * from mtg_karta; 
select * from barva;

select * from md_obsahuje where NOT exists (select * from deck where barvy = 'UB' AND deck_md_id = deck.id  );

SELECT * 
FROM md_obsahuje 
WHERE deck_md_id NOT IN (
     SELECT id FROM deck WHERE barvy='UB');

SELECT md_obsahuje.* 
FROM md_obsahuje JOIN (
         SELECT DISTINCT id FROM deck
         MINUS
         SELECT DISTINCT id FROM deck WHERE barvy='UB'
          ) deck ON deck.id=deck_md_id; 
        
update deck set druh_deck_id = (select id from druh where nazev = 'combo') where (id = 4);
update deck set druh_deck_id = (select id from druh where nazev = 'combo') where (id = 10);
update deck set druh_deck_id = (select id from druh where nazev = 'combo') where (id = 9);
update deck set druh_deck_id = (select id from druh where nazev = 'combo-control') where ( id = (select id from deck where jmeno = 'Drain Tendrils'));


select * from deck;
select * from druh;
        select id from druh where nazev = 'combo-control';
        select * from deck where (barvy='UB');
select * from deck where (barvy='UB') intersect select * from deck where druh_deck_id = (select id from druh where nazev = 'combo-control');          
          