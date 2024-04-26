-- テーブルの作成
CREATE TABLE examinee (
    examinee_id INT PRIMARY KEY AUTO_INCREMENT,
    examinee_name VARCHAR(20)
);

-- 人名生成用のサブクエリ
INSERT INTO examinee (examinee_name)
VALUES
('田中太郎'),
('佐藤花子'),
('鈴木健太'),
('山田美加'),
('伊藤次郎'),
('渡辺由美'),
('中村健介'),
('小林真理'),
('加藤拓也'),
('吉田麻衣'),
('山本大輔'),
('中島さちこ'),
('山口隆之'),
('松本由香'),
('井上悠香'),
('木村慎太郎'),
('林直子'),
('斎藤雅彦'),
('岡田絵美'),
('村上良子'),
('近藤啓介'),
('遠藤みゆき'),
('青木真一'),
('高橋理恵'),
('藤田健太'),
('西村奈々'),
('福田大輔'),
('田村由美子'),
('山下勇気'),
('中川和美'),
('小川大輔'),
('吉村優子'),
('岡本康介'),
('松田まり子'),
('佐々木隆太'),
('大久保さやか'),
('川村英樹'),
('清水彩子'),
('谷口真紀'),
('長谷川啓太'),
('石川麻衣子'),
('前田拓也'),
('小野寺朋子'),
('田辺健一'),
('藤本美智子'),
('三浦雄一'),
('河野千春'),
('村田悠真'),
('橋本香織');
