create or replace PROCEDURE VYPNI_CIZI_KLICE is
begin
 for CUR in (select CONSTRAINT_NAME , TABLE_NAME 
 	     from USER_CONSTRAINTS
	     where CONSTRAINT_TYPE = 'R' ) 
   LOOP
    execute immediate 'ALTER TABLE '||cur.table_name||' MODIFY CONSTRAINT "'||cur.constraint_name||'" DISABLE ';
   END LOOP;
end VYPNI_CIZI_KLICE;

