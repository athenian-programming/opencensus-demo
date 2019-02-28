# OpenCensus Demo


## Setup

* Start Prometheus with: `prometheus --config.file=etc/prometheus.yaml`

* Start Grafana on OSX with: 

```bash
brew services start grafana
```

or

```bash
grafana-server --config=/usr/local/etc/grafana/grafana.ini --homepath /usr/local/share/grafana cfg:default.paths.logs=/usr/local/var/log/grafana cfg:default.paths.data=/usr/local/var/lib/grafana cfg:default.paths.plugins=/usr/local/var/lib/grafana/plugins
```

## URLs

* OpenCensusDemo: http://localhost:8888
* Prometheus: http://localhost:9090
* Grafana: http://localhost:3000 (admin/admin)

## Screenshot

![screenshot](https://github.com/athenian-programming/opencensus-demo/raw/master/docs/dashboard.png "Grafana Dashboard Screenshot")
