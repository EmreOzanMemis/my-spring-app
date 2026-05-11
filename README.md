# 🚀 My Spring App

**Spring Boot** ve **Thymeleaf** kullanılarak geliştirilmiş, Azure App Service üzerinde çalışan modern bir Java web uygulaması.

---

## 📋 İçindekiler

- [Proje Hakkında](#-proje-hakkında)
- [Teknolojiler](#-teknolojiler)
- [Gereksinimler](#-gereksinimler)
- [Kurulum ve Çalıştırma](#-kurulum-ve-çalıştırma)
- [Docker ile Çalıştırma](#-docker-ile-çalıştırma)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Proje Yapısı](#-proje-yapısı)
- [Azure Kaynakları](#-azure-kaynakları)

---

## 📖 Proje Hakkında

Bu proje, Java ekosistemini ve Azure bulut hizmetlerini kullanarak modern bir web uygulaması geliştirmek amacıyla oluşturulmuştur. Uygulama; Spring Boot altyapısı, Thymeleaf şablon motoru ve Bootstrap 4 ile hazırlanmış responsive bir arayüze sahiptir.

---

## 🛠 Teknolojiler

| Teknoloji | Versiyon | Açıklama |
|-----------|----------|----------|
| Java | 1.8 | Temel programlama dili |
| Spring Boot | 2.1.2 | Web uygulama çatısı |
| Spring MVC | - | HTTP istek yönetimi |
| Thymeleaf | - | Sunucu taraflı HTML şablon motoru |
| Bootstrap | 4.2.1 | Responsive UI bileşenleri |
| Maven | 3.6.3 | Bağımlılık yönetimi ve derleme aracı |
| Docker | - | Konteyner desteği (multi-stage build) |
| Azure App Service | - | Bulut deployment platformu |

---

## ✅ Gereksinimler

Projeyi yerel ortamda çalıştırmak için aşağıdakilerin kurulu olması gerekmektedir:

- [Java 8 (JDK)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started) *(opsiyonel, konteyner ile çalıştırmak için)*

---

## ⚙️ Kurulum ve Çalıştırma

### 1. Repoyu Klonlayın

```bash
git clone https://github.com/EmreOzanMemis/my-spring-app.git
cd my-spring-app
```

### 2. Bağımlılıkları Yükleyin ve Derleyin

```bash
mvn clean install
```

### 3. Uygulamayı Başlatın

```bash
./mvnw spring-boot:run
```

Bu klonda Maven Wrapper dosyaları yoksa aynı komutu aşağıdaki şekilde çalıştırabilirsiniz:

```bash
mvn spring-boot:run
```

### 4. Tarayıcıda Açın

```
http://localhost:8080
```

---

## 🐳 Docker ile Çalıştırma

Uygulama, **multi-stage Docker build** desteklemektedir. Bu sayede derleme ve çalıştırma aşamaları ayrı katmanlarda gerçekleşir; üretim imajı daha küçük ve temiz olur.

### İmajı Oluşturun

```bash
docker build -t my-spring-app .
```

### Konteyneri Başlatın

```bash
docker run -p 8080:8080 my-spring-app
```

Uygulama `http://localhost:8080` adresinde çalışmaya başlayacaktır.

---

## 🔄 CI/CD Pipeline

Proje, **GitHub Actions** ile otomatik CI/CD pipeline'ına sahiptir. `master` branch'ine yapılan her push işleminde:

1. Maven ile uygulama derlenir ve testler çalıştırılır.
2. Başarılı derleme sonrasında **Azure App Service**'e otomatik olarak deploy edilir.

Pipeline yapılandırması: [`.github/workflows/master_javaappmaster.yml`](.github/workflows/master_javaappmaster.yml)

---

## 📁 Proje Yapısı

```
my-spring-app/
├── .github/
│   └── workflows/
│       └── master_javaappmaster.yml  # GitHub Actions CI/CD
├── src/
│   └── main/
│       ├── java/                     # Java kaynak kodları
│       └── resources/
│           └── templates/            # Thymeleaf HTML şablonları
├── Dockerfile                        # Multi-stage Docker build
├── pom.xml                           # Maven bağımlılıkları
└── README.md
```

---

## ✅ Task Demo Özellikleri

Uygulamadaki welcome sayfası artık basit bir görev yönetimi demosu da içerir:

- Yeni görev eklemek için bir form
- Görevleri anahtar kelimeyle filtrelemek için bir arama kutusu
- Veritabanı olmadan, bellek içinde tutulan görev listesi

---

## ☁️ Azure Kaynakları

Azure üzerinde Java ile çalışmaya başlamak için faydalı kaynaklar:

- 📦 [Azure Java Örnekleri](https://github.com/azure-samples?q=java&type=&language=&sort=)
- ⚡ [Java on App Service Hızlı Başlangıç](https://docs.microsoft.com/en-us/azure/app-service/quickstart-java?tabs=javase&pivots=platform-linux)
- 🌐 [Java ve CosmosDB Eğitimi](https://docs.microsoft.com/en-us/azure/app-service/tutorial-java-spring-cosmosdb)
- 🔧 [Azure Maven Plugin Dokümantasyonu](https://docs.microsoft.com/it-it/java/api/overview/azure/maven/azure-webapp-maven-plugin/readme?view=azure-java-stable#quick-start)

---

## 📄 Lisans

Bu proje MIT lisansı ile lisanslanmıştır.
