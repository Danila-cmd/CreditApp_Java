create table if not exists
    client (client_id UUID PRIMARY KEY NOT NULL,
            client_name varchar(45),
            phoneNumber varchar(45),
            email varchar(45),
            passportNumber varchar(45));