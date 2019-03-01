package com.dunya.orion.transaction.messages;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TxnActionsTest {


    @Test
    public void testShouldConvertToJson() {
        TxnActions txnActions = new TxnActions();
        assertThat(txnActions.toJson()).isNotBlank();
    }


}