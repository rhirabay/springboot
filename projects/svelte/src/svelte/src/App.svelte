<script>
    import Router from 'svelte-spa-router'
    import Home from "./components/Home.svelte";
    import Hello from "./components/Hello.svelte";
    import Svelte from "./components/Svelte.svelte";

    const routes = {
        '/': Home,
        '/hello': Hello,
        '/svelte': Svelte,
        '*': Home
    };
</script>

<Router routes={routes} />
