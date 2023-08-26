<script>
    import {
        closeModalOpen,
        currentInvoice,
        editInvoice,
        invoiceModalOpen,
        invoices,
    } from "../store";
    import { onMount, createEventDispatcher } from "svelte";
    import { Button, Input, Select } from "./shared";
    import { Icon, Trash, Plus } from "svelte-hero-icons";
    import { v4 as uuidv4 } from "uuid";
    import ErrorModal from "./ErrorModal.svelte";
    import CloseInvoiceModal from "./CloseInvoiceModal.svelte";
    import { InvoiceService } from "../generated";
    import { toast } from "svelte-french-toast";
    import Loading from "./Loading.svelte";
    import { Transition } from "@rgossiaux/svelte-headlessui";
    import {
        validateNotBlank,
        validatePositiveNumber,
        validateNotNull,
        validateForm,
    } from "../lib/validator";

    let errorModalIsOpen = false;
    let errors;
    let loading = false;
    const dispatch = createEventDispatcher();

    const submitForm = (ev) => {
        console.log(fields);
        if ($editInvoice) {
            updateInvoice();
        } else {
            uploadInvoice();
        }
    };

    const uploadInvoice = async () => {
        console.log(fields.invoiceItemList.length);
        let validate = validateForm(fields);
        const isFormValid = validate[0];
        errors = validate[1];

        if (!isFormValid) {
            errorModalIsOpen = true;
            console.log(errors);
            return;
        }

        loading = true;

        InvoiceService.createInvoice(fields)
            .then((res) => {
                console.log(res);
                toast.success("Invoice successfully created!", {
                    style: "border-radius: 120px; background-color: #4caf50; color: #fff;",
                    icon: "👏",
                });
                invoices.update((inv) => (inv = [...inv, res]));
                invoiceModalOpen.set(false);
            })
            .catch((err) => {
                console.error(err.body, "th");

                toast.error("Invoice creation failed!", {
                    style: "border-radius: 120px; background: #333; color: #fff;",
                });
            })
            .finally(() => {
                loading = false;
            });
    };

    const updateInvoice = async () => {
        if (
            fields.invoiceItemList.length <= 0 ||
            fields.invoiceItemList.some((item) => item.itemName === "")
        ) {
            //modal to fill out work items
            errorModalIsOpen = true;

            return;
        }
        loading = true;

        await InvoiceService.replaceById($currentInvoice.id, fields)
            .then(() => {
                toast.success("Invoice successfully updated!", {
                    style: "border-radius: 120px; background-color: #4caf50; color: #fff;",
                    icon: "👏",
                });
                editInvoice.set(false);
                currentInvoice.set(fields);
                invoiceModalOpen.set(false);
            })
            .catch((err) => {
                console.error(err, err.body);
                toast.error("Invoice updation failed!", {
                    style: "border-radius: 120px; background: #333; color: #fff;",
                });
            })
            .finally(() => {
                loading = false;
            });
    };

    const deleteInvoiceItem = (id) => {
        fields.invoiceItemList = fields.invoiceItemList.filter(
            (item) => item.id !== id
        );
    };

    const closeInvoice = (ev) => {
        dispatch("close");
        if ($editInvoice) editInvoice.set(false);
        console.log("closed");
    };

    const setPaymentDueDate = (payTerms) => {
        if (payTerms !== null) {
            const date = new Date();
            fields.paymentDueDate = new Date(
                date.setDate(date.getDate() + parseInt(payTerms))
            );
        }
    };

    const createInvoice = (ev) => {};

    const saveDraft = (ev) => {
        fields.status = "DRAFT";
    };

    const addNewInvoiceItem = (ev) => {
        fields.invoiceItemList = [
            ...fields.invoiceItemList,
            {
                id: uuidv4(),
                itemName: "",
                qty: 0,
                price: 0,
                total: 0,
            },
        ];
    };
    // $: invoiceTotal = fields.invoiceItemList.reduce(
    //     (acc, curr) => acc += curr.total, 0
    // );
    $: setPaymentDueDate(fields.paymentTerms);

    onMount(() => {
        console.log(fields.invoiceDate);
        console.log(loading);

        if ($editInvoice) {
            fields = $currentInvoice;
        }
    });

    let fields = {
        billerStreetAddress: "",
        billerCity: "",
        billerZipCode: null,
        billerCountry: "",
        clientName: "",
        clientStreetAddress: "",
        clientEmail: "",
        clientCity: "",
        clientZipCode: null,
        clientCountry: "",
        invoiceDate: new Date(),
        paymentTerms: null,
        paymentDueDate: null,
        productDesc: "",
        status: null,
        invoiceItemList: [],
    };
</script>

<div
    class="left-0 bg-transparent h-screen overflow-y-scroll [&::-webkit-scrollbar]:hidden [-ms-overflow-style:none] [scrollbar-width:none] lg:left-[5.62rem] w-full flex flex-col"
>
    <form
        class="relative p-14 max-w-[44rem] w-full bg-holderColor text-white shadow-xl"
        on:submit|preventDefault={submitForm}
    >
        <!-- {#if loading} -->
        <Transition
            show={loading}
            as="div"
            enter="transition ease-out duration-300"
            enterFrom="transform opacity-0"
            enterTo="transform opacity-100"
            leave="transition ease-in duration-200"
            leaveFrom="transform opacity-100"
            leaveTo="transform opacity-0"
        >
            <Loading />
        </Transition>
        <!-- {/if} -->

        <h1 class="font-semibold text-lg mb-12 text-white">
            {$editInvoice ? "Edit" : "New"}
            Invoice
        </h1>

        <div class="bill-from mb-12 flex flex-col">
            <h4 class="text-purple-800">Bill From</h4>

            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="text"
                    id="billerStreetAddress"
                    name="billerStreetAddress"
                    label="Street Address"
                    bind:value={fields.billerStreetAddress}
                />
            </div>

            <div class="location-details gap-4 flex">
                <div class="input flex flex-col mb-6 flex-1">
                    <Input
                        class="mb-6"
                        type="text"
                        id="billerCity"
                        name="billerCity"
                        label="City"
                        bind:value={fields.billerCity}
                    />
                </div>
                <div class="input flex flex-col mb-6 flex-1">
                    <Input
                        class="mb-6"
                        type="number"
                        label="Zip Code"
                        id="billerbillerZipCode"
                        name="billerZipCode"
                        bind:value={fields.billerZipCode}
                    />
                </div>
                <div class="input flex flex-col mb-6 flex-1">
                    <Input
                        class="mb-6"
                        type="text"
                        id="billerCountry"
                        name="billerCountry"
                        label="Country"
                        bind:value={fields.billerCountry}
                    />
                </div>
            </div>
        </div>

        <!-- Bill To -->
        <div class="bill-to mb-12 flex-col flex">
            <h4 class="text-[#7152f0] text-xs mb-6">Bill To</h4>

            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="text"
                    id="clientName"
                    name="clientName"
                    label="Client's Name"
                    bind:value={fields.clientName}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="text"
                    id="clientEmail"
                    name="clientEmail"
                    label="Client's Email"
                    bind:value={fields.clientEmail}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="text"
                    id="clientAddress"
                    name="clientAddress"
                    label="Client's Address"
                    bind:value={fields.clientStreetAddress}
                />
            </div>

            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="text"
                    label="Client's City"
                    id="clientCity"
                    name="clientCity"
                    bind:value={fields.clientCity}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="number"
                    id="clientZipCode"
                    name="clientZipCode"
                    label="Client's Zip Code"
                    bind:value={fields.clientZipCode}
                />
            </div>
            <div class="input flex flex-col mb-6">
                <Input
                    class="mb-6"
                    type="text"
                    id="clientCountry"
                    name="clientCountry"
                    label="Client's Country"
                    bind:value={fields.clientCountry}
                />
            </div>
        </div>

        <!-- Invoice Details -->
        <div class="invoice-details flex flex-col">
            <div class="payment flex gap-6">
                <div class="input flex flex-col mb-6 flex-1">
                    <Input
                        class="mb-6"
                        type="text"
                        id="invoiceDate"
                        name="invoiceDate"
                        label="Invoice Date"
                        disabled
                        value={new Date(fields.invoiceDate).toLocaleDateString(
                            "en-us",
                            {
                                year: "numeric",
                                month: "short",
                                day: "numeric",
                            }
                        ) ?? ""}
                    />
                </div>
                <div class="input flex flex-col mb-6 flex-1">
                    <Input
                        class="mb-6"
                        label="Payment Due Date"
                        type="text"
                        name="paymentDueDate"
                        id="paymentDueDate"
                        disabled
                        value={fields.paymentDueDate
                            ? fields.paymentDueDate.toLocaleDateString(
                                  "en-us",
                                  {
                                      year: "numeric",
                                      month: "short",
                                      day: "numeric",
                                  }
                              )
                            : ""}
                    />
                </div>
            </div>

            <div class="input flex flex-col mb-6 flex-1">
                <label for="paymentTerms">Payment Terms</label>
                <Select id="paymentTerms" bind:value={fields.paymentTerms}>
                    <option value={30}>Net 30 Days</option>
                    <option value={60}>Net 60 Days</option>
                    <option value={90}>Net 90 Days</option>
                </Select>
            </div>

            <div class="input flex flex-col mb-6 flex-1">
                <Input
                    class="mb-6"
                    type="text"
                    label="Product Description"
                    id="productDesc"
                    name="productDesc"
                    bind:value={fields.productDesc}
                />
            </div>

            <div class="items-list">
                <h3 class="text-[#777f98] text-base mb-4">Item List</h3>
                <table class="it-list w-full text-base">
                    <thead>
                        <tr class="t_heading mb-4 gap-4 text-xs flex">
                            <th class="text-left basis-2/4">Item Name</th>
                            <th class="text-left basis-1/12">Qty.</th>
                            <th class="text-left basis-1/5">Price</th>
                            <th class="text-left basis-1/5 self-center"
                                >Total</th
                            >
                        </tr>
                    </thead>
                    <tbody>
                        {#each fields.invoiceItemList as item, index (item.id)}
                            <tr
                                class="tb-items relative mb-6 gap-4 text-xs flex"
                            >
                                <td class="basis-2/4">
                                    <Input
                                        class="mb-6"
                                        type="text"
                                        bind:value={item.itemName}
                                    />
                                </td>
                                <td class="basis-1/12">
                                    <Input
                                        class="mb-6"
                                        type="number"
                                        bind:value={item.qty}
                                    />
                                </td>
                                <td class="basis-1/5">
                                    <Input
                                        class="mb-6"
                                        type="number"
                                        bind:value={item.price}
                                    />
                                </td>
                                <td
                                    class="flex basis-1/5 self-center mb-5 text-lg"
                                >
                                    $ {(item.total =
                                        item.qty * item.price).toFixed(2)}
                                </td>
                                <!-- svelte-ignore a11y-click-events-have-key-events -->
                                <td
                                    class="absolute top-2 h-6 w-5 right-0 cursor-pointer"
                                    on:click={() => deleteInvoiceItem(item.id)}
                                >
                                    <Icon src={Trash} />
                                </td>
                            </tr>
                        {/each}
                    </tbody>
                </table>

                <Button
                    type="button"
                    class="flex button text-white/85 bg-butCol items-center justify-center w-full !py-3 mt-5"
                    on:click={addNewInvoiceItem}
                >
                    <span class="w-5 h-5 mr-2.5">
                        <Icon src={Plus} />
                    </span>
                    Add New Item
                </Button>
            </div>
        </div>

        <div class="save flex mt-8">
            <div class="flex-1">
                <Button
                    type="button"
                    class="capitalize bg-red-600"
                    on:click={closeInvoice}
                >
                    cancel
                </Button>
            </div>
            <div class="flex justify-end flex-1 space-x-2">
                {#if !$editInvoice}
                    <Button
                        type="submit"
                        class="bg-butCol capitalize"
                        on:click={saveDraft}
                    >
                        save draft
                    </Button>
                    <Button
                        type="submit"
                        class="bg-purple-900 capitalize"
                        on:click={createInvoice}
                    >
                        Create Invoice
                    </Button>
                {:else}
                    <Button type="submit" class="bg-purple-900 capitalize">
                        Update Invoice
                    </Button>
                {/if}
            </div>
        </div>
    </form>
</div>

<ErrorModal
    isOpen={errorModalIsOpen}
    on:close={() => (errorModalIsOpen = false)}
>
    <!-- <span slot="description"> Input validation failed! </span> -->
    <ul slot="content" class="space-x-2 space-y-1 text-xs">
        {#each Object.entries(errors) as [fieldName, error]}
            {#if typeof error === "string"}
                <li>{error}</li>
            {:else if Array.isArray(error)}
                <li>
                    {fieldName}:
                    <ul>
                        {#each error as subError}
                            {#if subError}
                                {#each Object.entries(subError) as [subField, nestedSubErr]}
                                    {#if typeof error === "string"}
                                        <li>{nestedSubErr}</li>
                                    {/if}
                                {/each}
                            {/if}
                        {/each}
                    </ul>
                </li>
            {/if}
        {/each}
    </ul>
</ErrorModal>

<CloseInvoiceModal
    isOpen={$closeModalOpen}
    on:close={() => closeModalOpen.set(false)}
/>
