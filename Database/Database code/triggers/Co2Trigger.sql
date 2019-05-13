use Sep4
 Go


ALTER TRIGGER [dbo].[Co2ConfigureStatus]
ON [dbo].[Co2]
AFTER INSERT
AS
BEGIN 
	DECLARE @status AS varchar(15)
	DECLARE @max AS float = 1000
	DECLARE @min AS float = 0
	DECLARE @originalValue AS float

	UPDATE Co2 SET status = 'NORMAL'
		WHERE Co2.id IN (SELECT id FROM Inserted)
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
