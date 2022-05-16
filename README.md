# 25e5958b37189740927df99f8c7076fd


## Kullanılan Teknolojiler ve Kriterler:
- Minimum API Versiyonu: **21** kullanılmıştır.
- **MVVM** Mimarisi ile yazılmuıştır.
- Veritabanı işlemleri için **RoomDB** kullanılmıştır
- DI (Dependency Injection) için **Dagger-Hilt** kullanılmıştır
- Sayfa geçişleri ve roouting **Navigation** kullanılmıştır.
- Thread Management için **Coroutine** kullanılmıştır.
- UI tasarımı ve View katmanı **Jetpack Compose** kullanılmıştır.
- UI update işlemleri için **State** yapısı kullanılmıştır.


## Uyguulama Ekranları Hakkında

### Uygulamanın Çalışma Şekli
Uygulama ekranları **tek** activity üzerine kurulu navigation controller üzerinden çalışmaktadır ve 4 ekrandan oluşmaktadır. Bunlar;
- SplashScreen ile başlayan ufak logolu bir ekran
- Uzay Aracı Oluşturma Ekranı
- İstasyon ve Seyehat Etme Ekranı
- Favori İstasyonların Listelendiği Ekran

- Uygulama açıldığında SplashScreen' daki logo gösteriminden sonra Uzay Aracı Oluşturma ekranına gidiyor. Burada kullanıcının 
daha önce kaydetmiş olduğu araç ismi ve özellikler varsa otomatik kayıtlı olarak geliyor ve güncelleme yaparak ya da yapmayarak **Güncelle**
butonuna basıp bir sonraki ekrana geçebiliyor. Eğer daha önce kayıtlı bilgiler yoksa gerekli bilgileri doldurup **Devam Et** butonuna basarak da bir sonraki 
ekrana ilerleyebiliyor.

- Dayanıklılık, Hız ve Malzeme Kapasitesi SeekBar(Jetpack Compose' daki adi ile Slider) üzerinden seçilirken toplam 15 olarak ve slider' ların range değerleri **otomatik**
ayarlanacak şekilde kodlandı. Kısacası 3 Slider' ında range değerleri aynı değil. İlk Slider range değeri her zaman 1 ile 13 arasındayken diğer iki Slider' ın range değerleri ilk Slider' ın değerine göre
otomatik olarak ayarlanacak şekilde kodlandı

- İstasyonlar ve Seyahat etme ekranına geldikten sonra burada bizi verilen formüller sonucu çıkan UGS, EUS ve DS değerleriyle birlikte Uzay Araç ismi, Hasar Kapasitesi ve DS / 1000 oranından gelen kaç
saniye de bir hasar alacağı CountDownTimer gözüküyor. Hemen altında Search için bir TextField, sonrasında Horizontal bir LazyRow ve en altta ise Bulunduğumuz istasyonun adını gösteren Text yer alıyor.

- Seyahat Et butonuna bastıktan sonra case için veriler kurallar dahilinde logic işlemler yapılıyor ve UI güncellemeleri State değişkenler ile güncelleniyor. 

- Dünya' ya dönüş için bakılan kriterler;
	- Tüm istasyonların kapasitesi (need değeri) eğer 0 ise, yani tüm giyseler dağıtılşmış ve seyahat edilecek başka istasyon kalmadığında
	- Dağıtılacak giysiler bittiğinde (UGS değeri 0' a ulaştığında)
	- Evrensel Uzay Süresi dolduğunda (EUS değeri 0' a ulaştığında)
	- Uzay Aracının Hasar Kapasitesi bittiğinde (HK 0' a ulaştığında)
uyarı mesajı basılıp Dünya' ya dönülüyor ve tekrar Uzay Araçı özellikleri seçme ekranına geliyor

- Favori ekranında kullanıcının favoriye eklediği istasyonlar gözüküyor.

## Log Mekanizması
- Log Mekanizması olarak ViewModel katmanında **Timber** kullanılmıştır.
- Timber aracılığıyla hangi coroutine dispatcher üzerinde çalışıldığı ve bası response' lar logcat' de görünür haldedir. 

## Genel MVVM Yapısı
- Uygulama MVVM mimarisi üzerine kurulmuştur. ViewModel' den sonra **Repository Pattern** kodlanıp data' nın RoomDB' den mi yoksa API' den mi geleceğinin kontrol mekanizması bu repository layer' ında 
kodlandı
- İki çeşit ApiState' imiz mevcut. Proje' de yer alan **ApiState** sınıfı generic bir sınıf olan request sonucu dönen data için OnSuccess ve OnFailure methodları içermekte. Generic olarak Repository katmanına gelen
datayı döndürmektedir.
- **ApiViewState** ise repository' e gelen data' yı ya da RoomDB' den gelen datayı View katmanına ileten state yapımızdır.