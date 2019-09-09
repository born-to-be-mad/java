package by.dma1979.suppliers;

public class NamesSupplier implements Supplier<Stream<String>> {
private Path namesPath = Paths.get("server/src/main/resources/names.txt");
@Override
public Stream<String> get() {
try {
return Files.lines(namesPath);
} catch (IOException e) {
e.printStackTrace();
return null;
}
}
}