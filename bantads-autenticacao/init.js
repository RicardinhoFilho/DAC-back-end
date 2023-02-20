db.createUser({
    user: 'bantads',
    pwd: 'bantads',
    roles: [
        {
            role: 'readWrite',
            db: 'auth-db',
        },
    ],
});

db = new Mongo().getDB("auth-db");

db.createCollection('users', { capped: false });

db.users.insert([
    {
        "email": "admin@admin.com",
        "senha": "7a56551dac1cf054e7ea2ac50ddd9e694592a7f7d1a981b6cc7a94e71754db24aeaa3b036f50af3df9aa596214c2d80fe33afcea4a8cd1f0f99b7da68ffa685e",
        "cargo": "Administrador",
        "ativo": true
    }
]);