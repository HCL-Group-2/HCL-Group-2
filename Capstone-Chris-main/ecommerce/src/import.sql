DROP TRIGGER IF EXISTS `ecommerce`.upd_check;

DELIMITER $$
CREATE TRIGGER upd_check 
BEFORE UPDATE ON products
FOR EACH ROW
BEGIN 
	IF NEW.prod_inventory <= 0 THEN
		SET NEW.prod_inventory =  100 + NEW.prod_inventory;
	END IF;
END $$


DROP TRIGGER IF EXISTS `ecommerce`.QuantityUpdate;

DELIMITER |
CREATE TRIGGER  QuantityUpdate
AFTER INSERT
ON order_item 
FOR EACH ROW
	BEGIN
		UPDATE products
		SET products.prod_inventory = products.prod_inventory - New.prod_inventory 
		WHERE products.id = New.prod_id;
END |