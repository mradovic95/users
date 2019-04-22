set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER webshop_users PASSWORD 'password';
    CREATE DATABASE webshop_users_db;
    GRANT ALL PRIVILEGES ON DATABASE webshop_users_db TO webshop_users;
EOSQL