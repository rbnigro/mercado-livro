CREATE TABLE purchase (
    id int AUTO_INCREMENT PRIMARY KEY,
    customer_id int NOT NULL,
    nfe varchar(225),
    price DECIMAL(15,2) not null,
    created_at DATETIME not null,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE purchase_book (
    purchase_id int NOT NULL,
    book_id int NOT NULL,
    FOREIGN KEY (purchase_id) REFERENCES purchase(id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    PRIMARY KEY(purchase_id, book_id)
);
