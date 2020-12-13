package by.dma.entity;

import java.util.concurrent.locks.LockSupport;

/**
 * SLow implementation of {@link BankAccount}.
 *
 * @author dzmitry.marudau
 * @since 2020.4
 */
final public class SlowBankAccount extends BankAccount {
    public SlowBankAccount(double balance) {
        super(balance);
    }

    @Override
    protected void sleepAWhile() {
        LockSupport.parkNanos(50_000_000L);
    }
}
