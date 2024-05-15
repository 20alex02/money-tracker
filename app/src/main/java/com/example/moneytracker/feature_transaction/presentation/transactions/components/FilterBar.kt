package com.example.moneytracker.feature_transaction.presentation.transactions.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneytracker.feature_transaction.domain.model.CategoryViewModel
import com.example.moneytracker.feature_transaction.domain.util.Constants
import com.example.moneytracker.feature_transaction.domain.util.OrderType
import com.example.moneytracker.feature_transaction.domain.util.TransactionOrder
import com.example.moneytracker.feature_transaction.presentation.shared.input.CategoryChip
import com.example.moneytracker.feature_transaction.presentation.shared.input.CustomRadioButton

@Composable
fun FilterBar(
    modifier: Modifier,
    categories: List<CategoryViewModel>,
    transactionOrder: TransactionOrder = TransactionOrder.DATE,
    orderType: OrderType = OrderType.DESC,
    isExpenseFilter: Boolean? = null,
    categoryFilter: CategoryViewModel? = null,
    onFilterChange: (TransactionOrder, OrderType, Boolean?, CategoryViewModel?) -> Unit
) {
    Column {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            CustomRadioButton(
                text = TransactionOrder.DATE.columnName,
                selected = transactionOrder == TransactionOrder.DATE,
                onSelect = {
                    onFilterChange(
                        TransactionOrder.DATE,
                        orderType,
                        isExpenseFilter,
                        categoryFilter
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = TransactionOrder.TITLE.columnName,
                selected = transactionOrder == TransactionOrder.TITLE,
                onSelect = {
                    onFilterChange(
                        TransactionOrder.TITLE,
                        orderType,
                        isExpenseFilter,
                        categoryFilter
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = TransactionOrder.AMOUNT.columnName,
                selected = transactionOrder == TransactionOrder.AMOUNT,
                onSelect = {
                    onFilterChange(
                        TransactionOrder.AMOUNT,
                        orderType,
                        isExpenseFilter,
                        categoryFilter
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            CustomRadioButton(
                text = OrderType.ASC.text,
                selected = orderType == OrderType.ASC,
                onSelect = {
                    onFilterChange(
                        transactionOrder,
                        OrderType.ASC,
                        isExpenseFilter,
                        categoryFilter
                    )
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = OrderType.DESC.text,
                selected = orderType == OrderType.DESC,
                onSelect = {
                    onFilterChange(
                        transactionOrder,
                        OrderType.DESC,
                        isExpenseFilter,
                        categoryFilter
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            CustomRadioButton(
                text = Constants.ALL,
                selected = isExpenseFilter == null,
                onSelect = { onFilterChange(transactionOrder, orderType, null, categoryFilter) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = Constants.INCOME,
                selected = isExpenseFilter == false,
                onSelect = { onFilterChange(transactionOrder, orderType, false, categoryFilter) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = Constants.EXPENSE,
                selected = isExpenseFilter == true,
                onSelect = { onFilterChange(transactionOrder, orderType, true, categoryFilter) }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(contentPadding = PaddingValues(start = 10.dp)) {
            items(categories) { category ->
                CategoryChip(
                    categoryViewModel = category,
                    isSelected = category == categoryFilter,
                    onCategorySelected = {
                        onFilterChange(
                            transactionOrder,
                            orderType,
                            isExpenseFilter,
                            category
                        )
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}