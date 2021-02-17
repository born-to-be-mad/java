package by.dma.future;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Emulate synch and asynch deletion on different file servers with exceptions.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class ComputableFutureExceptionHandling {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        List<FileServer> fileServers = Arrays.asList(
                FileServer.fileServer("fAAA"), FileServer.fileServer("fBBB"),
                FileServer.webDavServer("ccc"), FileServer.webDavServer("ddd"), FileServer.webDavServer("eee"));

/*        System.out.println("#### delete SYNCH #####");
        new ComputableFutureExceptionHandling().deleteSynch(fileServers);*/
/*        System.out.println("#### delete ASYNCH via POOL SUBMIT #####");
        new ComputableFutureExceptionHandling().deleteAsynchThroughPoolSubmission(
                fileServers, Executors.newSingleThreadExecutor());*/
        System.out.println("#### delete ASYNCH via FUTURE #####");
        new ComputableFutureExceptionHandling().deleteAsynchThroughFuture(
                fileServers, Executors.newCachedThreadPool());
    }

    private void deleteAsynchThroughPoolSubmission(List<FileServer> fileServers, ExecutorService pool) {
        DocumentDeleter deleter = new FileServerDocumentDeleter();
        fileServers.forEach(fileServer -> pool.submit(() -> {
            try {
                deleter.deleteOnFileServer(fileServer);
            } catch (IOException e) {
                System.out.println("### IOException on " + fileServer + " :" + e);
            }
        }));
        pool.shutdown();
    }

    private void deleteAsynchThroughFuture(List<FileServer> fileServers, ExecutorService pool) {
        DocumentDeleter deleter = new FileServerDocumentDeleter();
        fileServers.forEach(fileServer ->
                                    CompletableFuture.runAsync(() -> {
                                        try {
                                            deleter.deleteOnFileServer(fileServer);
                                        } catch (IOException e) {
                                            System.out.println("### IOException on " + fileServer + " :" + e);
                                        }
                                    }, pool));
        System.out.println("Continue");
        pool.shutdown();
/*
        try {
            if (!pool.awaitTermination(3, TimeUnit.SECONDS)){
                pool.shutdownNow(); // Cancel currently executing tasks
                System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException e) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
*/
    }

    private void deleteSynch(List<FileServer> fileServers) {
        DocumentDeleter deleter = new FileServerDocumentDeleter();
        fileServers.forEach(fileServer -> {
            try {
                deleter.deleteOnFileServer(fileServer);
            } catch (IOException e) {
                System.out.println("### IOException on " + fileServer + " :" + e);
            }
        });
    }

    private void greetAsynch() {
        List<String> langList = Arrays.asList("EN", "ES", "SN", "EX");

        List<CompletableFuture<GreetHolder>> completableFutures =
                langList.stream()
                        .map(this::getGreeting)
                        .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]));

        CompletableFuture<List<GreetHolder>> allCompletableFuture = allFutures
                .thenApply(future -> completableFutures.stream()
                                                       .map(CompletableFuture::join)
                                                       .collect(Collectors.toList()));
        CompletableFuture completableFuture = allCompletableFuture
                .thenApply(greets -> greets.stream()
                                           .filter(Objects::nonNull)
                                           .map(GreetHolder::getGreet)
                                           .collect(Collectors.toList()));
        //completableFuture.get();
    }

    private CompletableFuture<GreetHolder> getGreeting(String lang) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.printf("Task execution started for %s%n", lang);
                Thread.sleep(1500 + ThreadLocalRandom.current().nextInt(500));
                System.out.printf("Task execution stopped for %s%n", lang);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new GreetHolder(getGreet(lang));
        }, executor).exceptionally(ex -> {
            System.err.printf("Something went wrong for %s:%s%n", lang, ex);
            return null;
        });
    }

    private String getGreet(String lang) {
        if (lang.equals("EN")) {
            return "Hello";
        } else if (lang.equals("ES")) {
            return "Hola";
        } else if (lang.equals("SN")) {
            return "Ayubovan";
        } else {
            throw new IllegalArgumentException("Invalid lang param");
        }
    }

    private static class GreetHolder {

        private String greet;

        public GreetHolder(String greet) {
            this.greet = greet;
        }

        public String getGreet() {
            return greet;
        }

        public void setGreet(String greet) {
            this.greet = greet;
        }
    }

    private interface DocumentDeleter {

        void deleteOnFileServer(FileServer fileServer) throws IOException;

        void deleteDirectoryIfExists(FileServer fileServer) throws IOException;
    }

    private class FileServerDocumentDeleter implements DocumentDeleter {

        AtomicInteger fileTry = new AtomicInteger(0);
        AtomicInteger directoryTry = new AtomicInteger(0);

        @Override
        public void deleteOnFileServer(FileServer fileServer) throws IOException {
            long time = System.nanoTime();
            int currentTry = fileTry.incrementAndGet();
            try {
                System.out.println("deleteOnFileServer try:" + currentTry);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1_000, 5_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean()) {
                    throw new IOException("deleteOnFileServer " + currentTry + " failed");
                }
            } finally {
                time = System.nanoTime() - time;
                System.out.printf("deleteOnFileServer %s finished in time = %dms%n", currentTry, (time / 1_000_000));
            }
        }

        @Override
        public void deleteDirectoryIfExists(FileServer fileServer) throws IOException {
            long time = System.nanoTime();
            int currentTry = directoryTry.incrementAndGet();
            try {
                System.out.println("deleteDirectoryIfExists try:" + currentTry);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1_000, 5_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ThreadLocalRandom.current().nextBoolean() && ThreadLocalRandom.current().nextBoolean()) {
                    throw new IOException("deleteDirectoryIfExists " + currentTry + " failed");
                }
            } finally {
                time = System.nanoTime() - time;
                System.out.printf("deleteDirectoryIfExists %s finished in time = %dms%n", currentTry,
                                  (time / 1_000_000));
            }
        }
    }

    private static class FileServer {

        private String url;

        private FileServerType serverType;

        public FileServer(String url, FileServerType serverType) {
            this.url = url;
            this.serverType = serverType;
        }

        public static FileServer webDavServer(String url) {
            return new FileServer(url, FileServerType.WEB_DAV);
        }

        public static FileServer fileServer(String url) {
            return new FileServer(url, FileServerType.FILE_SHARE);
        }

        @Override
        public String toString() {
            return "FileServer{" + "url='" + url + '\'' + ", serverType=" + serverType + '}';
        }
    }

    private enum FileServerType {
        FILE_SHARE,
        WEB_DAV
    }
}
