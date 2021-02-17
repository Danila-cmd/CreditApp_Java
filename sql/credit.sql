create table if not exists
    credit (credit_id UUID PRIMARY KEY NOT NULL,
            credit_limit INT,
            credit_interest_rate INT);