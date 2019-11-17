-- Table: public.subject

-- DROP TABLE public.subject;

CREATE TABLE public.subject
(
    id bigint NOT NULL DEFAULT nextval('subject_id_seq'::regclass),
    dna character varying(200) COLLATE pg_catalog."default" NOT NULL,
    mutant boolean NOT NULL,
    creationdate timestamp without time zone NOT NULL,
    CONSTRAINT subject_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.subject
    OWNER to mutantdb;