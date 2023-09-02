<header class="flex z-50 flex-row md:flex-col md:bg-[#353b68]/50 bg-[#353b68] lg:min-h-full w-16 lg:flex-col rounded-tbr-lg">
    <div class="flex rounded-br-2xl bg-violet-700 justify-center p-6 max-sm:w-28 max-sm:after:w-28 max-lg:h-full max-lg:w-32 max-lg:after:h-full max-lg:after:w-32 cursor-pointer max-[1000px]:h-32 max-[1000px]:after:h-32 max-[1000px]:after:top-16 w-full h-40 relative items-center overflow-hidden after:h-40 after:w-full after:absolute after:bg-[rgb(146,119,255)] after:top-20 after:left-0 after:rounded-tl-2xl">
        <div class="max-[1000px]:w-12 max-[1000px]:h-11 h-14 w-16 z-[1] relative">
            <img src="assets/logo.svg" alt="" class="w-auto h-7">
        </div>
    </div>
</header>

<aside>
    <div>
        <div class="flex rounded-br-2xl bg-violet-700 justify-center p-6 max-sm:w-28 max-sm:after:w-28 max-lg:h-full max-lg:w-32 max-lg:after:h-full max-lg:after:w-32 cursor-pointer max-[1000px]:h-32 max-[1000px]:after:h-32 max-[1000px]:after:top-16 w-full h-40 relative items-center overflow-hidden after:h-40 after:w-full after:absolute after:bg-[rgb(146,119,255)] after:top-20 after:left-0 after:rounded-tl-2xl">
            <div class="max-[1000px]:w-12 max-[1000px]:h-11 h-14 w-16 z-[1] relative">
                <img src="assets/logo.svg" alt="" class="w-auto h-7">
            </div>
        </div>

        <div class="flex flex-col items-center justify-center">
            <!-- svelte-ignore a11y-click-events-have-key-events -->
            <span on:click={toggleDarkMode}>
                {#if $dark}
                    <Icon class="w-6 h-6" src={Sun} />
                {:else}
                    <Icon class="w-6 h-6" src={Moon} />
                {/if}
            </span>
            <Avatar />
        </div>
    </div>
</aside>

<script>
    import {
        Icon,
        Sun,
        Moon,
    } from "svelte-hero-icons";
    import { dark } from "../store";
    import { onMount } from 'svelte';
    import Avatar from './Avatar.svelte';

    const toggleDarkMode = () => {
        const body = document.querySelector('body');

        if (body.classList.contains('dark')) {
            body.classList.remove('dark');
            localStorage.setItem('mode', 'light');
            dark.set(false);
        } else {
            body.classList.add('dark');
            localStorage.setItem('mode', 'dark');
            dark.set(true);
        }
    };

    onMount(() => {
        const mode = localStorage.getItem('mode');
        if (mode === 'dark') {
            document.querySelector('body').classList.add('dark');
            dark.set(true);
        }

        return () => {
            const body = document.querySelector('body');
            body.classList.remove('dark');
        };
    });
</script>