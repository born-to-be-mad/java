module hello.modules {
    exports by.dma.modules.hello;
    
    provides by.dma.modules.hello.HelloInterface with by.dma.modules.hello.HelloModules;
}
