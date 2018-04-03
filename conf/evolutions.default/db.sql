# --- !Ups
CREATE TABLE "users" (
  email VARCHAR NOT NULL PRIMARY KEY,
  password VARCHAR NOT NULL ,
  comment VARCHAR NULL,
  sLanguages VARCHAR NULL,
  pLanguage VARCHAR NOT NULL
);

# --- !Downs
DROP TABLE "users";