## Change Log

###v.1.0.4-SNAPSHOT
- Разделен интерфейс _TokenConverter_ на _TokenGenerator_ и _TokenParser_
- Разделен интерфейс _CredentialService_ на _CredentialService_ и _CredentialIdentityService_
- Добавлена реализация _CredentialIdentityService_ работащая без БД
- Добавлена работа с JWT токенами с RSA ключами

###v.1.0.3
- Добавлен класс _ApiUtils_ для последовательного получения всех ресурсов через API
- Добавлено поле domain в DomainException
- Модуль _rcore-domain-role_ переехал в _rcore-domain-auth_
- Добавлены стандартные _TokenConverter_ для _JWT_
- Fix по работе с учетными данными