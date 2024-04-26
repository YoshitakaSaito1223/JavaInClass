-- テーブルの作成
CREATE TABLE examinee (
    examinee_id INT PRIMARY KEY AUTO_INCREMENT,
    examinee_name VARCHAR(20)
);

-- 人名生成用のサブクエリ
WITH RECURSIVE random_names AS (
    SELECT 
        CONCAT(
            (SELECT SUBSTRING('田中佐藤鈴木山田', 1 + FLOOR(RAND() * 5) * 2, 2)),
            (SELECT SUBSTRING('一二三四五六七八九十', 1 + FLOOR(RAND() * 10), 1))
        ) AS name
    UNION ALL
    SELECT CONCAT(
            (SELECT SUBSTRING('田中佐藤鈴木山田', 1 + FLOOR(RAND() * 5) * 2, 2)),
            (SELECT SUBSTRING('一二三四五六七八九十', 1 + FLOOR(RAND() * 10), 1))
        ) AS name
    FROM random_names LIMIT 49
)

-- サンプルデータの挿入
INSERT INTO examinee (examinee_name)
SELECT name FROM random_names;
