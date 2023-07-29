# Rick and Morty Karakterleri Uygulaması

Bu, güncel kütüphaneler kullanılarak geliştirilmiş bir çizgi film karakterleri uygulamasıdır. Uygulama, https://rickandmortyapi.com/api/ adresinin sağladığı verileri kullanır.

## Kullanılan Teknolojiler

- Retrofit & Coroutine: HTTP isteklerini yapmak ve verileri almak için kullanılan bir kütüphane ve asenkron işlemleri yönetmek için Coroutine kullanılır.
- Room Database: Uygulama verilerini yerel olarak depolamak için kullanılan bir SQLite nesne ilişkisel eşleme (ORM) kütüphanesidir.
- Pagination Framework: Büyük veri setlerini sayfalara bölmek ve verileri parçalı olarak yüklemek için kullanılan bir kütüphane.
- MVVM (Model-View-ViewModel): Uygulama mimarisi olarak MVVM kullanılır. Bu mimaride veri kaynağı (Model), kullanıcı arayüzü (View) ve iş mantığı (ViewModel) ayrı ayrı ele alınır.
- Fragment Navigation Framework: Uygulama içindeki gezinmeyi yönetmek için kullanılan bir kütüphane.
- Glide Extension: İnternetten resimleri yüklemek ve görüntülemek için kullanılan bir resim işleme kütüphanesidir.

## Kurulum

1. Bu depoyu yerel makinenize klonlayın.
2. Android Studio'yu açın ve projeyi içe aktarın.
3. Gerekli bağımlılıkları indirmek için Gradle Sync'i çalıştırın.

## Kullanım

1. Uygulamayı çalıştırın.
2. Ana ekranda çizgi film karakterlerini görüntüleyin.
3. Karakterler hakkında ayrıntılı bilgileri görmek için bir karaktere dokunun.
4. Favori karakterinizi kayıt edin ve listeleyin.
5. Eğer isterseniz favori karakterinizi sola kaydırarak silin.

## Örnek Kullanım Kodu

Uygulama içinde Retrofit ile API istekleri yapılır ve veriler Room Database'e kaydedilir. Örnek kullanım kodu aşağıdaki gibi olabilir:

```kotlin
// API isteği yapmak için Retrofit service'ini oluşturun
val retrofit = Retrofit.Builder()
    .baseUrl("https://rickandmortyapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(ApiService::class.java)

// Karakterleri almak için bir istek yapın
val response = service.getCharacters()

// İstek sonucunu işleyin ve verileri Room Database'e kaydedin
response.enqueue(object : Callback<CharacterResponse> {
    override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
        if (response.isSuccessful) {
            val characters = response.body()?.results
            // Verileri Room Database'e kaydedin
            // ...
        }
    }

    override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
        // Hata durumunda işlemleri ele alın
        // ...
    }
})
```
## Katkıda Bulunma
Eğer bu projeye katkıda bulunmak isterseniz, lütfen CONTRIBUTING.md dosyasını inceleyin ve pull request göndermeden önce geliştirme adımlarını takip edin.

## Teşekkürler
Bu uygulama, Rick and Morty API'si ve kullanılan kütüphaneler sayesinde oluşturulmuştur. Proje için geliştiricilere teşekkür ederiz.
