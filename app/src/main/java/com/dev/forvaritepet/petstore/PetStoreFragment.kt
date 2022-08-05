package com.dev.forvaritepet.petstore

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.billingclient.api.*
import com.dev.forvaritepet.R
import com.dev.forvaritepet.adapters.PetsAdapter
import com.dev.forvaritepet.databinding.FragmentPetStoreBinding
import com.google.common.collect.ImmutableList

class PetStoreFragment : Fragment(), PurchasesUpdatedListener {

    private lateinit var binding: FragmentPetStoreBinding

    private var billingClient: BillingClient? = null


    private lateinit var listSku: List<SkuDetails?>

    private var skuTest = ""

    private var productDetails: ProductDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetStoreBinding.inflate(layoutInflater)
        billingClient = context?.let {
            BillingClient.newBuilder(it)
                .enablePendingPurchases()
                .setListener { billingResult: BillingResult?, list: List<Purchase?>? -> }
                .build()
        }
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                Log.d("AAAA", "Connected")
//                queryProducts()
            }

            override fun onBillingServiceDisconnected() {
            }
        })

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PetsAdapter(
            this,
        ) {
                purchase -> {}
            queryProducts(purchase.position)
        }

        binding.petsList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }



    private fun queryProducts(position : Int) {
        val productList: MutableList<QueryProductDetailsParams.Product> = ArrayList()

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("pack1")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("pack2")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("pack3")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("pack4")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("pack5")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("pack6")
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )


        val queryProductDetailsParams =
            QueryProductDetailsParams.newBuilder()
                .setProductList(
                    productList
                )
                .build()

        billingClient!!.queryProductDetailsAsync(
            queryProductDetailsParams,
            ProductDetailsResponseListener { billingResult, productDetailsList ->
                productDetails = productDetailsList[position]
                skuTest = productDetailsList[position].toString()
                Log.d("skuTest", skuTest)

                val billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(
                        ImmutableList.of(
                            productDetails?.let {
                                BillingFlowParams.ProductDetailsParams.newBuilder()
                                    .setProductDetails(it)
                                    .build()
                            }
                        )
                    )
                    .build()
                val billingResult = billingClient?.launchBillingFlow(requireActivity(), billingFlowParams)

            }
        )


    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                val consumeParams =
                    ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.purchaseToken)
                        .build()


                val listener = ConsumeResponseListener { billingResult, purchaseToken ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        val inflater = this.requireActivity().getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE
                        ) as LayoutInflater
                        val v: View = inflater.inflate(R.layout.thank_popup, null)
                        AlertDialog.Builder(this.requireActivity())
                            .setIcon(R.mipmap.ic_launcher)
                            .setView(v)
                            .setTitle("Thank you")
                            .setMessage("Have a nice day!")
                            .show()
                    }
                }
                billingClient!!.consumeAsync(consumeParams, listener)

            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
        } else {
            // Handle any other error codes.
        }

    }


}