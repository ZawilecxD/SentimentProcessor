-- DO usuwania duplikatow w tabelce w bazie
DELETE FROM posts
WHERE id IN (SELECT id
              FROM (SELECT id,
                             ROW_NUMBER() OVER (partition BY id ORDER BY id) AS rnum
                     FROM posts) t
              WHERE t.rnum > 1);

