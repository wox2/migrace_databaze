create or replace
PROCEDURE ZAPNI_CIZI_KLICE is
begin
 FOR cur IN (select CONSTRAINT_NAME, TABLE_NAME 
             from USER_CONSTRAINTS
	     where CONSTRAINT_TYPE = 'R' ) 
  LOOP
   execute immediate 'ALTER TABLE '||cur.table_name||' MODIFY CONSTRAINT "'||cur.constraint_name||'" ENABLE ';
  END LOOP;
end ZAPNI_CIZI_KLICE;

