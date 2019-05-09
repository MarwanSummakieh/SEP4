use Sep4
 Go


CREATE TRIGGER [dbo].[Co2ConfigureStatus]
ON [dbo].[Co2]
AFTER INSERT
AS
BEGIN 
	DECLARE @status AS varchar(15)
	DECLARE @max AS INT = 1000
	DECLARE @min AS INT = 0
	DECLARE @originalValue AS INT


	SELECT @status = status
	FROM Inserted

	SELECT @originalValue = value
	FROM Inserted
	IF (@originalValue  > 1000)
		UPDATE Co2
		SET status = 'HIGH'
		WHERE Co2.id IN (SELECT id FROM Inserted) 
		ELSE IF(@originalValue  < 0)
		UPDATE Co2
		SET status = 'LOW'
		WHERE Co2.id IN (SELECT id FROM Inserted)


END
