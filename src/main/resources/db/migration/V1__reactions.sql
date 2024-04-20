create table if not exists reactions
(
    id           uuid primary key,
    sender_id    uuid          not null,
    recipient_id uuid          not null,
    comment      varchar(2048) not null,
    prompt_id    uuid          not null,
    sent_at      timestamp     not null,
    state        varchar(255)  not null
);