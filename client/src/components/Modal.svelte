<script>
// import { fade } from 'svelte/transition';
import { 
    Dialog, 
    DialogOverlay, 
    DialogTitle, 
    Transition, 
    TransitionChild,
} from '@rgossiaux/svelte-headlessui';
import { createEventDispatcher, } from "svelte";

const dispatch = createEventDispatcher();

export let isOpen;
export let extraOverlayClasses = '';

const closeModal = (ev) => {
    dispatch('close');
};
</script>

<Transition
    show={isOpen}
    as="div" 
>
    <Dialog
        class="relative z-50 font-poppins"
        on:close={closeModal}
    >
        <TransitionChild
            as="div"
            enter="transition ease-out duration-300" 
            enterFrom="transform opacity-0"
            enterTo="transform opacity-100"
            leave="transition ease-in duration-200" 
            leaveFrom="transform opacity-100" 
            leaveTo="transform opacity-0"
        >
            <DialogOverlay class="fixed inset-0 bg-gray-500 bg-opacity-75 {extraOverlayClasses}" />
        </TransitionChild>

        <div class="fixed inset-0 overflow-y-auto max-w-sm mx-auto my-auto h-max shadow-xl rounded-2xl">
            <div class="flex min-h-full items-center justify-center text-center">

                <TransitionChild
                    class="w-full"
                    as="div"
                    enter="transition ease-out duration-300" 
                    enterFrom="transform scale-90 opacity-0"
                    enterTo="transform scale-100 opacity-100"
                    leave="transition ease-in duration-200" 
                    leaveFrom="transform scale-100 opacity-100" 
                    leaveTo="transform scale-90 opacity-0"
                >
                    <div class="w-full max-w-md overflow-hidden rounded-2xl text-white bg-butCol py-12 px-8 align-middle shadow-xl">
                        <slot />
                    </div>
                </TransitionChild>

            </div>
                    
        </div>
        
    </Dialog>
</Transition>