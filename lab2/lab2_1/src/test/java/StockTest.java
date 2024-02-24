import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.tqs.IStockmarketService;
import ua.tqs.StocksPortfolio;
import ua.tqs.Stock;

@ExtendWith(MockitoExtension.class)
public class StockTest {
    
    @Mock
    IStockmarketService mockMarket;

    @InjectMocks
    StocksPortfolio stockPortofolio;

    @BeforeEach
    public void SetUp(){
        stockPortofolio = new StocksPortfolio(mockMarket);
    }

    @DisplayName("test for the totalValue")
    @Test
    public void testTotalValue() {
        when(mockMarket.lookUpPrice("bananas")).thenReturn(0.80);
        when(mockMarket.lookUpPrice("kinder bueno")).thenReturn(1.30);
        when(mockMarket.lookUpPrice("ice tea manga")).thenReturn(0.93);

        stockPortofolio.addStock(new Stock("bananas", 3));
        stockPortofolio.addStock(new Stock("kinder bueno", 1));
        stockPortofolio.addStock(new Stock("ice tea manga", 2));

        double result = stockPortofolio.totalValue();
        assertEquals(5.56, result, 0.0001);

       // mockito verifies argument values in natural java style: by using an equals() method
        verify(mockMarket, times(3)).lookUpPrice(anyString()); // porque o segundo argumento? - verifica quantas vezes o mock foi chamado
    }
}
