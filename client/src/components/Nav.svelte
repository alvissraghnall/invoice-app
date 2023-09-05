<aside class="relative z-20">
    <div class="h-screen w-[6.5rem] rounded-r-2xl fixed flex justify-between flex-col md:bg-[#373b53]/95 bg-[#373b53] max-[1000px]:w-24 max-md:h-24 max-lg:h-28 max-lg:w-full max-lg:flex-row max-lg:rounded-none max-sm:h-20 dark:bg-[rgb(30,33,57)]">
        <div class="flex rounded-r-2xl bg-violet-700 justify-center p-6 max-sm:w-20 max-sm:after:w-20 max-lg:h-full max-lg:w-24 max-lg:after:h-full max-lg:after:w-24 cursor-pointer max-[1000px]:h-28 max-[1000px]:after:h-28 w-full h-32 relative items-center overflow-hidden after:h-32 after:w-full after:absolute after:bg-[rgb(146,119,255)] after:top-1/2 max-lg:after:top-2/4 after:left-0 after:rounded-tl-2xl">
            <div class="max-[1000px]:w-12 max-[1000px]:h-11 h-14 w-16 z-[1] relative flex justify-center items-center">
                <img src="/assets/logo.svg" alt="" class="w-auto h-8">
            </div>
        </div>

        <div class="flex flex-col items-center justify-center gap-6 max-lg:flex-row">
            <!-- svelte-ignore a11y-click-events-have-key-events -->
            <span on:click={toggleDarkMode} class="text-4xl cursor-pointer hover:text-[#dfe3fa] max-lg:mb-0 text-[#7e88c3] dark:text-[#858bb2]">
                <Icon class="w-6 h-6" src={$dark ? Sun : Moon} />
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