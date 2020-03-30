INSERT INTO `user_bank` (`id`, `email`, `last_name`, `first_name`, `password`, `account_id`) VALUES
(1,	'1@test.com', 'Pietrzak', 'Aurélien', '$2a$10$ISB3/qQmucSyFbt8KBHO8.lE76qfJ9QCC0aB5Jyorpd2WVaMByxEa', null),
(2,	'2@test.com', 'Jourdain', 'Nicolas', '$2a$10$ISB3/qQmucSyFbt8KBHO8.lE76qfJ9QCC0aB5Jyorpd2WVaMByxEa', null),
(3,	'3@test.com', 'Amazon', '', '$2a$10$ISB3/qQmucSyFbt8KBHO8.lE76qfJ9QCC0aB5Jyorpd2WVaMByxEa', null);

INSERT INTO `account` (`id`, `balance`, `card_number`, `user_id`) VALUES
(1,	2353.42, '1111-2222-3333-4444',	1),
(2,	-353.42, '1111-2222-3333-5555' , 2),
(3,	3244, '1111-2222-3333-6666', 3);

UPDATE user_bank SET account_id = 1 WHERE id = 1;
UPDATE user_bank SET account_id = 2 WHERE id = 2;
UPDATE user_bank SET account_id = 3 WHERE id = 3;

INSERT INTO `transactions` (`id`, `amount`, `date`, `account_id`) VALUES
(1,	353, NOW(), 1),
(2,	53.82, NOW(), 1),
(3,	-34.00, NOW(), 1),
(4,	-353.42, NOW(), 2),
(5,	3.42, NOW(), 2),
(6,	-53.42, NOW(), 2),
(7,	44, NOW(), 3),
(8,	311, NOW(), 3),
(9,	-4.53, NOW(), 3);
