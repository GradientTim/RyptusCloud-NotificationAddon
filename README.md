# RyptusCloud - NotificationAddon

Official ***RyptusCloud - Notification*** CloudAddon by RyptusMedia (StonksCloud [Tim Kiesel]).

Website: [RyptusMedia](https://RyptusMedia.de)   
Twitter: [@RyptusMediaDE](https://twitter.com/RyptusMediaDE)

## Usage

1) Download the latest [RyptusCloud](https://github.com/RyptusMedia/RyptusCloud) file.
2) Make sure, the directory "***./RyptusCloud/storage/addons/proxy/***" is exist.
3) Paste the addon in the target directory from step 2.
4) Restart the RyptusCloud or restart the current Group.
5) The addon will works on the network, if you have the target permissions:

| Permissions |
| :---: |
| ryptuscloud.notification.notify.* |
| ---+ or +--- |
| ryptuscloud.notification.notify.starting |
| ryptuscloud.notification.notify.online |
| ryptuscloud.notification.notify.stopping |
| ryptuscloud.notification.notify.stopped |

| Placeholder | Description | Type |
| :---: | :---: | :---: |
| %serviceName% | Get the name of the service. | String |
| %nodeService% | Get the name of the node. | String |
| %serviceId% | Get the id from the service. | String |
| %serviceGameId% | Get the gameId from the service. | String |
| %serviceGroup% | Get the CloudGroup name of the service. | String |
| %servicePort% | Get the port of the service. | Integer |
