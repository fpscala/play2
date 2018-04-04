# --- !Ups
CREATE TABLE "users" (
  email VARCHAR NOT NULL PRIMARY KEY,
  password VARCHAR NOT NULL
);

ALTER TABLE "users" ADD COLUMN "comment" VARCHAR NULL;
ALTER TABLE "users" ADD COLUMN "pLanguage" VARCHAR NULL;
ALTER TABLE "users" ADD COLUMN "sLanguages" JSONB NULL;

# --- !Downs
DROP TABLE "users";
ALTER TABLE "users" DROP "comment";
ALTER TABLE "users" DROP "pLanguage";
ALTER TABLE "users" DROP "sLanguages";
