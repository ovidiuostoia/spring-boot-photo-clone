# spring-boot-photo-clone
An introduction dummy project into spring boot framework

## Good to know
* spring-boot framework app which uses `models`, `controllers`, `services` and `repositories`

### Models
* a model is a class that has an equivalent table in the db (see `Photos` class)
* it has to be annotated with `@Table(<table_name>)`

```java
@Table("PHOTOS")
public class Photo {
    @Id
    private Integer id;

    @NotEmpty 
    private String fileName;
    private String contentType;
    
    @JsonIgnore 
    private byte[] data;
}
```

### Controllers
* in your controller classes you will define yor api (see `PhotoCrudController`)
* the class has to be annotated with `@RestController`
* it usually has a service class (eg. `PhotoService`) used to delegate crud operations
* every kind of http reqest has its own anottation
    * `@GetMapping("/api/path")` for GET reqests
    * `@DeleteMapping("/api/path/{id}")` for DELETE reqests
    * `@PostMapping("/api/path")` for POST reqests
    * `@PutMapping("/api/path/{id}")` for PUT reqests
* annotate method parameter with `@PathVariable` to get the `id` from `/api/path/{id}`
* if you send a json as payload in a post or put requuest, annotate the method parameter with `@RequestBody`
* annotate method parameter with `@Valid` to ensure the payload is valid
* use `@RequestPart("data") MultipartFile file` in a put reqest if you send other data than json

```java
    @GetMapping("/photos")
    public Iterable<Photo> getPhotos() {
        return this.photoService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhoto(@PathVariable Integer id) {
        Photo photo = this.photoService.getPhoto(id);
        if (photo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return photo;
    }
    
    @DeleteMapping("/photos/{id}")
    public void deletePhoto(@PathVariable Integer id) {
        this.photoService.deletePhoto(id);
    }

    @PostMapping("/photos")
    public Photo addPhoto(@RequestPart("data") MultipartFile file ) throws IOException {
        return this.photoService.addPhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

    @PutMapping("/photos/{id}")
    public Photo updatePhoto(@PathVariable Integer id, @RequestBody @Valid Photo updatedPhoto) {
        return this.photoService.updatePhoto(id, updatedPhoto);
    }
```

### Services
* these classes are used for `CRUD` operations on your models (see `PhotoService`)
* the class has to be annotated with `@Service`
* it usually has a repository (eg. `PhotoRepository`) that represents the content of your storage (or DB)

```java
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }
}
```

### Repositories
* used to handle the local storage
* the `interface` has to be annotated with `@Repository` and it extends `CrudRepository` interface
* it handles by defalt all crud operations on the DB

## Static content
* in `src/main/resorces/static` path all html content will be placed
* `src/main/resorces/application.properties` file is used to pass properties to the spring-boot app

## Run & Deploy
* run the app with `mvn spring-boot:run`
* deploy the app with `mvn clean package`