INSERT INTO `user_ecommerce`(`id`, `email`, `password`, `bankCardNumber`, `name`, `firstname`) VALUES (1, '1@test.com', '1234','1111-2222-3333-4444', 'Pietrzak', 'Aurélien');
INSERT INTO `user_ecommerce`(`id`, `email`, `password`, `bankCardNumber`, `name`, `firstname`) VALUES (2, '2@test.com', '1234','1111-2222-3333-5555', 'Jourdain', 'Nicolas');

INSERT INTO `products`(`id`, `name`, `description`, `urlPictures`, `owner_id`, `available`, `price`) VALUES (1, 'Téléphone', 'Vraiment un joli téléphone','https://wee-static.com/cache/5/product/580x580/0000/0030/308882-telephone-retro-746-terracotta-wild-wolf.jpg', 1, true, 123.44);
INSERT INTO `products`(`id`, `name`, `description`, `urlPictures`, `owner_id`, `available`, `price`) VALUES (2, 'Voiture', 'Vraiment une jolie voiture','https://img-4.linternaute.com/9AvndEAhmrHTCjj5I4scY1nOmGo=/1240x/smart/a48cb0f6fa8641f68b8d4de5cd7ff6e8/ccmcms-linternaute/10654613.jpg', 1, true, 16.44);
INSERT INTO `products`(`id`, `name`, `description`, `urlPictures`, `owner_id`, `available`, `price`) VALUES (3, 'Télévision', 'Vraiment une jolie télévision','https://www.picclickimg.com/d/l400/pict/173900924471_/Rare-Ancien-Poste-de-Television-DUCRETET-THOMSON-Vintage.jpg', 2, true, 67.45);
INSERT INTO `products`(`id`, `name`, `description`, `urlPictures`, `owner_id`, `available`, `price`) VALUES (4, 'Lit', 'Vraiment un jolie lit','https://lesvieilleschoses.com/144751-large_default/lit-ancien.jpg', 2, true, 12.88);

