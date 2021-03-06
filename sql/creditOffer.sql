create table if not exists
    creditOffer (credit_id UUID PRIMARY KEY NOT NULL,
            client_data varchar(250),
            credit_data varchar(250),
            credit_amount INT,
            credit_year INT);