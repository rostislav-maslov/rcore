## Change Log

###v.1.0.4
- Разделен интерфейс _TokenConverter_ на **_TokenGenerator_** и **_TokenParser_**
- Разделен интерфейс _CredentialService_ на **_CredentialService_** и **_CredentialIdentityService_**
- Добавлена реализация _CredentialIdentityService_, работащая без обращение в хранилище
- Добавлена работа с JWT токенами, зашифрованными с помощью RSA ключа
- Убрана аннотация @Component для реализаций _TokenGenerator_ и _TokenParser_
- Добалвен интерфейс _DataMapper_
- Исправлен повторный вызов методов при аутентификации в spring-security
- Настройка времени жизни токенов вынесеноа в отделый класс _TokenLifeCycleConfig_ для возможности переопределения значений на уровне приложения

###v.1.0.3
- Добавлен класс _ApiUtils_ для последовательного получения всех ресурсов через API
- Добавлено поле domain в DomainException
- Модуль _rcore-domain-role_ переехал в _rcore-domain-auth_
- Добавлены стандартные _TokenConverter_ для _JWT_
- Fix по работе с учетными данными