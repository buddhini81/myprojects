CREATE TABLE IF NOT EXISTS job ( 
   id INT auto_increment NOT NULL, 
   job_title VARCHAR(100) NOT NULL, 
   company_name VARCHAR(100) NOT NULL, 
   job_board VARCHAR(100), 
   ref_board VARCHAR(100), 
   post_url VARCHAR(200),
   applied_date DATE, 
   comment VARCHAR(200)
);