CREATE TABLE Booking (
  id INT PRIMARY KEY     NOT NULL,
  reservation_start           timestamp     NOT NULL,
  reservation_end            timestamp      NOT NULL,
  email        CHAR(50),
  phone        CHAR(15),
  status      CHAR(50)

);



